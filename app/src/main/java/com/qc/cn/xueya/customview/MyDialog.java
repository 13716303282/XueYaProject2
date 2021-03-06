package com.qc.cn.xueya.customview;

/*
 * Copyright © 1999-2014 byecity, Inc. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.qc.cn.xueya.R;


/*****************************************
 * @ClassName: MyDialog
 * @Package com.xywy.common.widget
 * @Description: TODO(用一句话描述该文件做什么)
 * @author CodeMonkeyCui
 * @date 2014年11月7日 上午9:29:41
 * @version V1.0
 *******************************************/
public class MyDialog extends Dialog {

	public MyDialog(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
		// TODO Auto-generated constructor stub
		initDialog(context);
	}

	public MyDialog(Context context, int theme) {
		super(context, theme);
		// TODO Auto-generated constructor stub
		initDialog(context);
	}

	public MyDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		initDialog(context);
	}

	private void initDialog(Context context) {
		setCanceledOnTouchOutside(false);//点击dialog以外不会消失
		Window window = getWindow();
		window.setWindowAnimations(R.style.dialogWindowAnim);
		WindowManager.LayoutParams params = window.getAttributes();
		params.gravity = Gravity.CENTER;
		params.width = WindowManager.LayoutParams.MATCH_PARENT;
		window.setAttributes(params);
	}
}
