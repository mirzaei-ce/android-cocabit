package com.cocabitwallet.presenter.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cocabitwallet.R;
import com.cocabitwallet.presenter.activities.IntroActivity;
import com.cocabitwallet.tools.animation.BRAnimator;
import com.cocabitwallet.tools.manager.SharedPreferencesManager;
import com.cocabitwallet.tools.security.PostAuthenticationProcessor;
import com.cocabitwallet.wallet.BRWalletManager;

/**
 * BreadWallet
 * <p>
 * Created by Mihail Gutan <mihail@breadwallet.com> on 9/15/15.
 * Copyright (c) 2016 breadwallet LLC
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

public class IntroNewWalletFragment extends Fragment {
    private static final String TAG = IntroNewWalletFragment.class.getName();
    public Button introGenerate;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        final View rootView = inflater.inflate(R.layout.intro_fragment_new_wallet, container, false);
        introGenerate = (Button) rootView.findViewById(R.id.intro_new_wallet_generate);
        introGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BRAnimator.checkTheMultipressingAvailability()) {
                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            BRWalletManager m = BRWalletManager.getInstance(getActivity());
                            m.wipeWalletButKeystore(getActivity());
                            m.wipeKeyStore(getActivity());
                            SharedPreferencesManager.putAllowSpend(getActivity(), true);
                            PostAuthenticationProcessor.getInstance().onCreateWalletAuth((IntroActivity) getActivity(), false);
                        }
                    });
                }
            }
        });
        return rootView;
    }

}