package com.my.app.schoollifesystem.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.my.app.schoollifesystem.R;
import com.my.app.schoollifesystem.base.BaseCoreFragment;
import com.my.app.schoollifesystem.common.MyApplication;
import com.my.app.schoollifesystem.presenter.TalkPresenter;
import com.my.app.schoollifesystem.util.TcpClient;
import com.my.app.schoollifesystem.util.ThreadPoolUtils;

/**
 * Created by yf on 18-5-23.
 */

public class FragmentTalk extends BaseCoreFragment<TalkPresenter> {

    private LinearLayout talkContainer;
    private EditText talkText;
    private Button talkBtn;

    @Override
    public TalkPresenter getPresenter() {
        return mPresenter == null ? new TalkPresenter() : mPresenter;
    }

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Log.i("jeff",msg.what+"");
            switch (msg.what){
                case 1:
                    addPeopleTalk(msg.obj);
                    break;
                case 2:
                    addSystemMsg(msg.obj);
                    break;
            }
        }
    };

    private void addSystemMsg(Object obj) {
//        showFragmentToast((String) obj);
    }

    private void addPeopleTalk(Object obj) {
        com.my.app.schoollifesystem.bean.Message message = (com.my.app.schoollifesystem.bean.Message) obj;
        View view = LayoutInflater.from(getContext()).inflate(R.layout.talk_layout,null);
        LinearLayout leftTalk = view.findViewById(R.id.leftTalk);
        LinearLayout rightTalk = view.findViewById(R.id.rightTalk);
        TextView leftTalkName = view.findViewById(R.id.leftTalkName);
        TextView leftTalkContent = view.findViewById(R.id.leftTalkContent);
        TextView rightTalkName = view.findViewById(R.id.rightTalkName);
        TextView rightTalkContent = view.findViewById(R.id.rightTalkContent);
        MyApplication application = (MyApplication) getContext().getApplicationContext();
        if(message!=null && !TextUtils.isEmpty(message.getName())){
            if(application.getUser().getUserName().equals(message.getName())){
                leftTalk.setVisibility(View.VISIBLE);
                rightTalk.setVisibility(View.GONE);
                leftTalkName.setText(message.getName());
                leftTalkContent.setText(message.getContent());
            }else{
                if(!message.getName().equals("系统消息")){
                    leftTalk.setVisibility(View.GONE);
                    rightTalk.setVisibility(View.VISIBLE);
                    rightTalkName.setText(message.getName());
                    rightTalkContent.setText(message.getContent());
                }else{
//                    showFragmentToast(message.getContent());
                }
            }
        }else{

        }
        talkContainer.addView(view);
    }

    @Override
    protected void initView(View view) {
        new Thread(TcpClient.instance()).start();

        talkContainer = view.findViewById(R.id.talkContainer);
        talkText = view.findViewById(R.id.talkText);
        talkBtn = view.findViewById(R.id.sendBtn);

        TcpClient.instance().setMyHandler(mHandler);

        talkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(talkText.getText().toString())){
                    SendMsgThread sendMsgThread = new SendMsgThread();
                    sendMsgThread.setContentText(talkText.getText().toString());
                    talkText.setText("");
                    ThreadPoolUtils.instance().execute(sendMsgThread);
                }else{
                    showFragmentToast(R.string.textCanNotNull);
                }
            }
        });
    }

    @Override
    protected int getLayoutXml() {
        return R.layout.fragment_talk_layout;
    }

    private class SendMsgThread implements Runnable{

        String mContentText;

        @Override
        public void run() {
            MyApplication application = (MyApplication) getContext().getApplicationContext();
            TcpClient.instance().sendMsg(mContentText,application.getUser().getUserName());
        }

        public void setContentText(String contentText) {
            mContentText = contentText;
        }
    }
}
