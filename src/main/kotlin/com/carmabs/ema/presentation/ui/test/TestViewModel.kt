package com.carmabs.ema.presentation.ui.test

import com.carmabs.ema.core.viewmodel.EmaViewModel


class TestViewModel: EmaViewModel<TestState,TestNavigator.Navigation>(){
	
	override fun onStartFirstTime(statePreloaded: Boolean) {
    
    }

    override fun onResume(firstTime:Boolean){

    }

   override val initialViewState: TestState = TestState()
   
}
