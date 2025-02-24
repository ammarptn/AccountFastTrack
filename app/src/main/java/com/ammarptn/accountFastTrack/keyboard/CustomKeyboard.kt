package com.ammarptn.accountFastTrack.keyboard

import android.inputmethodservice.InputMethodService
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.ammarptn.accountFastTrack.R
import com.ammarptn.accountFastTrack.domain.usecase.GetAccountsUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class CustomKeyboard : InputMethodService(), CoroutineScope {

    @Inject
    lateinit var getCardsUseCase: GetAccountsUseCase

    private lateinit var rootView: View
    private lateinit var viewPager: ViewPager2
    private lateinit var dotsLayout: LinearLayout

    private var currentDots: Array<ImageView>? = null

    private val cardPreviewAdapter = AccountPreviewAdapter()
    private var isViewInitialized = false

    private val job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main


    override fun onCreate() {
        super.onCreate()
        // Move card collection to after view initialization
        launch {
            getCardsUseCase().collect { cards ->
                cardPreviewAdapter.updateCards(cards)
                if (isViewInitialized) {
                    updateDots(cards.size)
                }
            }
        }
    }

    override fun onCreateInputView(): View {
        rootView = layoutInflater.inflate(R.layout.keyboard_root_layout, null)
        setupCardPreview(rootView)
        isViewInitialized = true
        // Initial update of dots for any existing cards
        updateDots(cardPreviewAdapter.itemCount)

        return rootView
    }

    private fun setupCardPreview(rootView: View) {
        viewPager = rootView.findViewById(R.id.viewPager)
        dotsLayout = rootView.findViewById(R.id.dotsIndicator)

        viewPager.adapter = cardPreviewAdapter

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                updateSelectedDot(position)
            }
        })

        // Back button
        rootView.findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            (application.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager).showInputMethodPicker()
        }

        // Clear button
        rootView.findViewById<ImageView>(R.id.btnClear).setOnClickListener {
            clearCurrentInput()
        }


        //Card Holder Name Button
        rootView.findViewById<TextView>(R.id.btnAccountUsername).setOnClickListener {
            val currentPosition = viewPager.currentItem
            cardPreviewAdapter.getSelectedAccount(currentPosition)?.let { card ->
                clearCurrentInput()
                currentInputConnection?.commitText(card.username, 1)
            }
        }

        // Card Number button
        rootView.findViewById<TextView>(R.id.btnPassword).setOnClickListener {
            val currentPosition = viewPager.currentItem
            cardPreviewAdapter.getSelectedAccount(currentPosition)?.let { card ->
                clearCurrentInput()
                currentInputConnection?.commitText(card.password, 1)
            }
        }


    }


    private fun updateDots(cardCount: Int) {
        if (!::dotsLayout.isInitialized) return

        dotsLayout.post {
            dotsLayout.removeAllViews()
            if (cardCount > 0) {
                currentDots = Array(cardCount) { index ->
                    ImageView(this).apply {
                        setImageResource(
                            if (index == 0) R.drawable.dot_selected
                            else R.drawable.dot_unselected
                        )
                        val params = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        ).apply {
                            marginStart = 8
                            marginEnd = 8
                        }
                        layoutParams = params
                    }
                }
                currentDots?.forEach { dot ->
                    dotsLayout.addView(dot)
                }
            }
        }
    }

    // Add clearCurrentInput method
    private fun clearCurrentInput() {
        // Get current cursor position and text length
        val ic = currentInputConnection ?: return

        // Select all text
        ic.performContextMenuAction(android.R.id.selectAll)

        // Delete selected text
        ic.commitText("", 1)
    }

    private fun updateSelectedDot(position: Int) {
        if (!::dotsLayout.isInitialized) return

        currentDots?.forEachIndexed { index, dot ->
            dot.setImageResource(
                if (index == position) R.drawable.dot_selected
                else R.drawable.dot_unselected
            )
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }


}
