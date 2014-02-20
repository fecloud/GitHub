package com.aspire.mose.frame.net.connect.socket;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import android.util.Log;

import com.aspire.mose.frame.net.connect.IConnect;
import com.aspire.mose.frame.net.connect.IConnectErrorListener;
import com.aspire.mose.frame.net.connect.IConnectInputListener;

/**
 * socket 网络连接实现类
 * @author liangbo
 *
 * 2011-8-1 下午01:50:37
 *  
 * SocketConnect
 *
 */
public class SocketConnect implements IConnect {

	public static final String TAG = "SocketConnect";

	private final int READ_BUFF_SIZE = 255; // 每次读取网络流缓冲大小

	// 关闭MsgMgr后,关闭网络连接,等待一点时间从而关闭网络读取线程的等待时间
	private final int CLOSE_THREAD_WART_TIME = 500;

	// 服务器socket断开后,read函数非阻塞,读取错误判断次数,如果大于这个次数,则网络模块通知网路断开
	private final int SOCKET_READ_ERROR_MAX = 3;

	// 收到消息处理器
	IConnectInputListener connectInputListener = null;

	// 网络错误通知器
	IConnectErrorListener connectErrorListener = null;

	// 连接参数
	protected String[] urlInfo = null;

	// 连接器是否有效
	private boolean valid = false;

	// socket
	private Socket m_Socket = null;

	// 输入流
	private DataInputStream m_Din = null;

	// 输出流
	private DataOutputStream m_Dout = null;

	// 输入流读取线程判断标志位
	private boolean m_RecvThreadRun = false;
	
	boolean canFlush = true;

	//private SocketConnect instance;
	
	
	Thread m_RecvThread = null;

	/**
	 * 判断连接是否有效，需要和connect 、release函数同步
	 * 
	 * @return boolean
	 */
	public synchronized boolean isValid() {
		return valid;
	}

	public void setConnectInputListener(
			IConnectInputListener connectInputListener) {
		this.connectInputListener = connectInputListener;
	}

	/**
	 * 构造函数
	 * 
	 * @param msgMgrSocket
	 *            MsgMgrSocket 消息管理器对象
	 */
	public SocketConnect(IConnectInputListener connectInputListener) {
		this.connectInputListener = connectInputListener;
		//instance = this;
	}

	/**
	 * 构造函数
	 * 
	 */
	public SocketConnect() {
		//instance = this;
	}

	@Override
	/*
	 * * 建立连接，需要和 isValid 、release函数同步
	 * 
	 * @param urlInfo String[] 连接参数
	 * 
	 * @return boolean 连接是否成功
	 */
	public synchronized int connect(String[] urlInfo) {
		
		//设置未知错误
		int bFlag = ERROR_NONE;

		// 参数保护，检查输入参数是否有效
		if (urlInfo == null || urlInfo.length < 2 || urlInfo[0] == null
				|| urlInfo[1] == null) {

			// "连接参数错误 urlInfo[]" + urlInfo);
			Log.d(TAG, "connect() Connection parameter error urlInfo[]="
					+ urlInfo);
			bFlag = ERROR_CONNECT_PARAMENTER_INVALID;//错误的连接参数
			return bFlag;
		}
		
		
		//检测连接参数是否一致
//		 if(connect.checkoutConnect(urlInfo))
//		 {
//			 //如果一致 则判断是否已经连接了 
//			 if(connect.isValid())
//			 {
//				 //如果已经连接了 则不在连接
//			 }
//		 }
//		 if(connect.isValid())
//		 {
//			 
//			 //connect.
//			 boolean flag = connect.connect(urlInfo);
//		 }
		

		try {
			m_Socket = new Socket();
			//m_Socket = new Socket(urlInfo[0], Integer.parseInt(urlInfo[1]));
			InetAddress address = InetAddress.getByName(urlInfo[0]);
			
			SocketAddress socketAddress = new InetSocketAddress(address,Integer.parseInt(urlInfo[1]));
			

			m_Socket.connect(socketAddress,5000);

			m_Dout = new DataOutputStream(m_Socket.getOutputStream());
			m_Din = new DataInputStream(m_Socket.getInputStream());
			bFlag = ERROR_SUCCESS;
		} catch (UnknownHostException e) {
			Log.d(TAG, "connect() connect error socket://" + urlInfo[0]
					+ "  port:" + urlInfo[1] + "UnknownHostException = " + e);
			bFlag = ERROR_HOST_INVALID;//无效的主机地址
		}
		catch (SocketTimeoutException e) {
			Log.d(TAG, "connect() connect error socket://" + urlInfo[0]
					+ "  port:" + urlInfo[1] + "IOException = " + e);
			e.printStackTrace();
			bFlag = ERROR_CONNECT_TIMEOUT;//连接超时
		}
		
		catch (IOException e) {
			Log.d(TAG, "connect() connect error socket://" + urlInfo[0]
					+ "  port:" + urlInfo[1] + "IOException = " + e);
			e.printStackTrace();
			bFlag = ERROR_GET_STREAM_INVALID;//获取输入输出流失败
		}

		if (bFlag == ERROR_SUCCESS) {
			// 启动接受消息线程
			m_RecvThreadRun = true;
			recvStart();

			// 更新当前的连接参数
			this.urlInfo = urlInfo;

			// 设置当前的连接器为有效可以使用
			valid = true;
			Log.d(TAG, "connect() connect succeed socket://" + urlInfo[0]
					+ "  port:" + urlInfo[1]);
		}

		return bFlag;

	}

	/**
	 * 启动网络输入线程
	 */
	private void recvStart() {
	    m_RecvThread = new Thread() {
			public void run() {
				// 网络数据读入线程开始
				Log.d(TAG, "recvStart()  net recv Thread begin");

				// 错误读取次数计数器
				int readLenErrorCount = 0;

				// int NET_PACK_COUNT = 0; //累计读取的多少次网络包
				// int MSGGROUP_COUNT = 0; //累计解析了多少个消息组

				// 如果读取线程开关打开
				while (m_RecvThreadRun) {
					// 建立临时的缓冲流
					ByteArrayOutputStream bout = new ByteArrayOutputStream();
					try {

						// 定义每次读取缓冲数组
						byte[] buff = new byte[READ_BUFF_SIZE];
						int len = 0;
						// MsgMgrFactory.debugInfo("网络数据读入线程 接受数据中......");

						do {
							if (m_Din != null) {
								// 同步保证读取的时候不会被关闭
								synchronized (m_Din) {
									// try {
									// long startTime =
									// System.currentTimeMillis();
									// long waittime = 10;
									len = m_Din.read(buff, 0, READ_BUFF_SIZE);
									// 若遇见网络不稳定,则休息片刻后再读数据
									// if (System.currentTimeMillis() - 2000 >
									// startTime) {
									// waittime = 500;
									// }
									// try {
									// Thread.sleep(waittime);
									// }
									// catch (InterruptedException e) {
									// e.printStackTrace();
									// }

									// }
									// catch (Exception e) {
									// //Controler.strError += e.toString();
									// throw e;
									// }
								}
							} else {
								len = -1;
							}

							// MsgMgrFactory.debugInfo("revc byte len:" + len);
							if (len > 0) {
								// 成功读取,则设置连续错误读取数字清零
								readLenErrorCount = 0;
								// 写入缓冲流
								bout.write(buff, 0, len);
							} else {
								// 读取失败,则设置连续错误读取数字累加
								readLenErrorCount++;
							}
							// MsgMgrFactory.debugInfo("m_Din.available()=" +
							// m_Din.available());
							if (len <= READ_BUFF_SIZE && m_Din != null
									&& m_Din.available() == 0) {
								break;
							}
						} while (len > 0);

						// MsgMgrFactory.debugInfo("网络数据读入线程 接受一次网络数据成功 readLenErrorCount="
						// +
						// readLenErrorCount);

						// MsgMgrFactory.debugInfo("网络数据读入线程 接受一次网络数据成功次数 ="+(NET_PACK_COUNT++));

						// 因为j2me网络如果服务器断开链接,则read函数就变为非阻塞,
						// 所以这里要判断是否超过规定读取错误次数来判断是否网络断开
						Log.d(TAG, "readLenErrorCount  ="+readLenErrorCount);
						if (readLenErrorCount >= SOCKET_READ_ERROR_MAX) {
							m_RecvThreadRun = false;
							// 调用网络断线错误通知函数
							if (connectErrorListener != null) {
							 synchronized (connectErrorListener) {
								 connectErrorListener.connectException(IConnectErrorListener.CONNECT_ERROR_SERVER_DIS,
							 		"SERVER _ DIS");
							 	}
							 }
							
							// 网络异常断线						
							Log.d(TAG, "NET RECV THREAD connectError");
							//跳出读取线程的循环
							break;
						}

						// 解析数据
						buff = null;
						buff = bout.toByteArray();
						
//						Log.d(TAG, "MSG 1 ==="+new String(buff,"UTF-8"));
						
						
						bout.close();
						bout = null;
						// DataInputStream in = new DataInputStream(new
						// ByteArrayInputStream(
						// buff));
						// while (in.available() != 0) {

						if (buff != null && buff.length > 0
								&& connectInputListener != null) {
							
							
							
							//synchronized (connectInputListener) {
								connectInputListener.receive(buff);
							//}
						}

						// MsgGroup outMg = new MsgGroup();
						// outMg.decode(in);
						// MsgMgrFactory.debugInfo("网络数据读入线程 解析得到组的成功次数 ="+(MSGGROUP_COUNT++));
						// 返回消息处理,同步相映处理,广播处理
						// reciveRespons(outMg);
						// }
					} catch (Exception e) {
						// 网络数据读入线程 网络数据读入线程异常"+e.toString()+e.getMessage());
						Log.d(TAG, "NET RECV THREAD Exception e="
								+ e.toString() + e.getMessage());
						m_RecvThreadRun = false;
						e.printStackTrace();
						if (connectErrorListener != null) {
							 synchronized (connectErrorListener) {
								 connectErrorListener.connectException(IConnectErrorListener.CONNECT_ERROR_RECVTHRAD_EXCEPTION,
							 		"NET IMPUT EXCEPTION");
							 	}
							 }
						//跳出读取线程的循环
						break;
					} finally {
						if (bout != null) {
							try {
								bout.close();
							} catch (IOException ex) {
								ex.printStackTrace();
							}
							bout = null;
						}
					}
				}
				// 网络数据读入线程 结束
				
				// 关闭整个MsgMgr
				// if (msgMgrSocket != null ) {
				// msgMgrSocket.readThreadRelease(instance);
				// }
				//如果是读取线程异常引起的结束 则也要更新整个连接的状态 但是不需要像外部的使用者一样再去停止线程
				if(this.equals(m_RecvThread))
				{
				    
				    readThreadRelease();
				}
				
			}
		};
		
		m_RecvThread.start();
	}
	
	/**
	 * 清除关闭连接
	 */
	private synchronized void readThreadRelease() {
        Log.d(TAG, "readThreadRelease()");
		close(false);
	}

	/**
	 * 清除关闭连接
	 */
	@Override
	public synchronized void release() {
        Log.d(TAG, "release()");
		close(true);
	}

	// 清除关闭当前的msgMgr,内部使用，不做同步，
	// 防止在setURL中调用release 造成release 和setURL这两个同步函数死锁的可能性
	private void close(boolean closeReadThread) {

		// MsgMgrFactory.debugInfo("------开始关闭Connect");
		Log.d(TAG, "close() close Connect begin ");
		// 设置当前管理器为不可以使用
		valid = false;

		// 判断是否需要关闭 网络数据流读取线程,如果在网络数据流读取线程自己关闭了,则怒需要此处再设置
		if (closeReadThread) {
			// 设置读入流线程的标志为关闭，从尔关闭之前的读入线程
			m_RecvThreadRun = false;
		}
		
		if(m_RecvThread!=null)
		{
		    try
            {
                m_RecvThread.stop();
                Log.d(TAG, "close() close Connect 0: Clear the m_RecvThread ");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally {
                m_RecvThread = null;
            }
		}
		

		// 清除输入流
		if (m_Din != null) {

			try {
				m_Din.close();
				// 关闭连接 清除输入流
				Log.d(TAG, "close() close Connect 1: Clear the m_Din ");
			} catch (IOException ex) {
				// 关闭连接 清除输入流异常
				Log.d(TAG,
						"close() close Connect 1: Clear the m_Din IOException ex="
								+ ex.getMessage());
			} finally {
				m_Din = null;
			}

		}

		// 清除输出流
		if (m_Dout != null) {

			try {
				m_Dout.close();
				// 关闭连接 清除输出流
				Log.d(TAG, "close() close Connect 2: Clear the m_Dout ");
			} catch (IOException ex1) {
				// 关闭连接 清除输出流异常
				Log.d(TAG,
						"close() close Connect 2: Clear the m_Dout IOException ex1="
								+ ex1.getMessage());
			} finally {
				m_Dout = null;
			}

		}

		// 清除连接
		if (m_Socket != null) {
			try {
				m_Socket.close();
				// 关闭连接 清除连接
				Log.d(TAG, "close() close Connect 3: Clear the m_Socket ");
			} catch (IOException ex2) {
				// 关闭连接 清除连接异常
				Log.d(TAG,
						"close() close Connect 3: Clear the m_Socket IOException ex2="
								+ ex2.getMessage());
			} finally {
				m_Socket = null;
			}

		}

		// 判断是否停止 网络数据读入流线程
		if (closeReadThread) {
			// 暂停一段时间，让读入流线程有机会停止
			try {
				// 关闭连接 暂停一段时间，让读入流线程有机会停止
				Log
						.d(TAG,
								"close() close Connect 4: wart time form stop NET RECV THREAD ");
				Thread.sleep(CLOSE_THREAD_WART_TIME);
			} catch (Exception ex3) {
				// 关闭MsgMgr 清除时候，暂停一段时间异常
				Log.d(TAG,
						"close() close Connect 4: wart time form stop NET RECV THREAD Exception ex3="
								+ ex3.getMessage());
			}
		}

		// 清除内存
		System.gc();

		// ------完成关闭Connect
		Log.d(TAG, "close() close Connect done ");
	}

	@Override
	public synchronized int send(byte[] b) {
		
		int  bFlag = ERROR_NONE;//未知错误
		if (m_Dout != null) {
			try {
				m_Dout.write(b);
				// 三星手机测试不能使用flush函数
				if (canFlush) {
					// 准备 m_Dout.flush()
					Log.d(TAG, "send()  m_Dout.flush() begin");
					m_Dout.flush();
					// m_Dout.flush() 成功
					Log.d(TAG, "send()  m_Dout.flush() done");
				}
				bFlag = ERROR_SUCCESS;//成功
			} catch (IOException ex) {
				bFlag = ERROR_OUT_IOEXCEPTION;//写出IO异常
				//写流错误
				Log.d(TAG, "send()  m_Dout.write  IOException"
						+ ex.getMessage());
			}
		} else {
			bFlag = ERROR_OUT_STREAM_NULL;//输出流为空
		}
		return bFlag;
	}

	@Override
	public void setConnectErrorListener(IConnectErrorListener listener) {
		this.connectErrorListener = listener;

	}

	@Override
	public boolean checkoutConnect(String[] urlInfo) {
		if(this.urlInfo.equals(urlInfo))//这里需要注意实现
		{
			return true;
		}
		return false;
	}

}
