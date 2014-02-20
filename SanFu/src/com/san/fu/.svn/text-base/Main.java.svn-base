/**
 * 
 */
package com.san.fu;

import com.san.fu.contrl.UserLoginContrl;
import com.san.fu.contrl.UserNameCreateContrl;
import com.san.fu.contrl.UserRegContrl;
import com.san.fu.contrl.UserTpContrl;
import static com.san.fu.Const.THREAD_NUM;

/**
 * @author oyf_feng
 * 
 */
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        new UserNameCreateContrl().start(THREAD_NUM);
        new UserRegContrl().start(THREAD_NUM);
        new UserLoginContrl().start(THREAD_NUM);
        new UserTpContrl().start(THREAD_NUM);
    }
}
