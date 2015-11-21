package com.henglu.haiway.httpUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.http.Header;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Environment;
import android.widget.Toast;
import com.henglu.haiway.R;
import com.henglu.haiway.util.Config;
import com.henglu.haiway.util.Constant;
import com.henglu.haiway.util.Util;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;
public class HttpUtil {
	private static AsyncHttpClient client;
	private static HttpUtil instance;
	private Context context;
	private HttpUtil(Context context) {
		client = new AsyncHttpClient();
		client.setTimeout(20000);
		PersistentCookieStore myCookieStore = new PersistentCookieStore(context);  
		client.setCookieStore(myCookieStore);
		this.context = context;
	}
	public static HttpUtil instance(Context context) {
		if (instance == null) {
			instance = new HttpUtil(context);
		}
		return instance;
	}
	
	public boolean isNetworkConnected(){
		if(!Util.isNetworkConnected(context)){
			Toast.makeText(context, context.getResources().getString(R.string.error_network), Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}

	public void get(String urlString, AsyncHttpResponseHandler res) {
		if(!isNetworkConnected()){
			return;
		}
		client.get(urlString, res);
	}
	
	public void post(String urlString, ResponseHandlerInterface res) {
		if(!isNetworkConnected()){
			return;
		}
		client.post(urlString, res);
	}

	public void get(String urlString, RequestParams params,
			AsyncHttpResponseHandler res) {
		if(!isNetworkConnected()){
			return;
		}
		client.get(urlString, params, res);
	}
	
	public void post(String urlString, RequestParams params,
			ResponseHandlerInterface res) {
		if(!isNetworkConnected()){
			return;
		}
		client.post(urlString, params, res);
	}
	
	public void post(String urlString, RequestParams params,
			JsonHttpResponseHandler res) {
		if(!isNetworkConnected()){
			return;
		}
		client.post(urlString, params, res);
	}

	public void get(String urlString, JsonHttpResponseHandler res) {
		if(!isNetworkConnected()){
			return;
		}
		client.get(urlString, res);
	}

	public void get(String urlString, RequestParams params,
			JsonHttpResponseHandler res) {
		if(!isNetworkConnected()){
			return;
		}
		client.get(urlString, params, res);
	}

	public void get(String uString, BinaryHttpResponseHandler bHandler) {
		if(!isNetworkConnected()){
			return;
		}
		client.get(uString, bHandler);
	}

	public AsyncHttpClient getClient() {
		return client;
	}
	
	public void downloadFile(String url,final ProgressDialog progressDialog,final AsyncHttpResponseHandler handler){
		if(!isNetworkConnected()){
			return;
		}
		client.get(url, new AsyncHttpResponseHandler() {
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				if(!progressDialog.isShowing())
				progressDialog.show();
				super.onStart();
			}
			
			@Override
			public void onCancel() {
				// TODO Auto-generated method stub
				super.onCancel();
				if(progressDialog.isShowing())
				progressDialog.dismiss();
			}
			
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				super.onFinish();
				if(progressDialog.isShowing())
				progressDialog.dismiss();
				handler.onFinish();
			}
			@Override
			public void onSuccess(int statusCode, Header[] arg1, byte[] binaryData) {
				if(statusCode == 200){
					String tempPath = Environment.getExternalStorageDirectory()  
			                .getPath() + File.separator +Constant.APP_NAME;
					File file = new File(tempPath);  
					progressDialog.setProgress(0);
					try {
						FileOutputStream fileOutputStream = new FileOutputStream(file);
						fileOutputStream.write(binaryData);
						fileOutputStream.close();
						fileOutputStream.flush();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
			}
			
			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(context, context.getString(R.string.download_error), Toast.LENGTH_LONG).show();  
				
			}
			
			@Override
			public void onProgress(int bytesWritten, int totalSize) {
				// TODO Auto-generated method stub
				super.onProgress(bytesWritten, totalSize);
				int count = (int) ((bytesWritten * 1.0 / totalSize) * 100);  
		        // 下载进度显示  
				progressDialog.setProgress(count);  
			}
			
			
		});
	}
	
	public void downloadFile(String url,final String fileName,final ProgressDialog progressDialog,final AsyncHttpResponseHandler handler){
		if(!isNetworkConnected()){
			return;
		}
		client.get(url, new AsyncHttpResponseHandler() {
			@Override
			public void onStart() {
				if(!progressDialog.isShowing())
					progressDialog.show();
				super.onStart();
			}
			
			@Override
			public void onCancel() {
				// TODO Auto-generated method stub
				super.onCancel();
				if(progressDialog.isShowing())
				progressDialog.dismiss();
			}
			
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				super.onFinish();
				if(progressDialog.isShowing())
				progressDialog.dismiss();
				handler.onFinish();
			}
			@Override
			public void onSuccess(int statusCode, Header[] arg1, byte[] binaryData) {
				if(statusCode == 200){
					File file = Util.getFileByPath(Config.APP_DATA_PATH,fileName);
					progressDialog.setProgress(0);
					try {
						FileOutputStream fileOutputStream = new FileOutputStream(file);
						fileOutputStream.write(binaryData);
						fileOutputStream.close();
						fileOutputStream.flush();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
			}
			
			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
				if(arg0==404){
					Toast.makeText(context, "文件不存在", Toast.LENGTH_SHORT).show();
					return;
				}
				Toast.makeText(context, context.getString(R.string.download_error), Toast.LENGTH_LONG).show();  
			}
			
			@Override
			public void onProgress(int bytesWritten, int totalSize) {
				// TODO Auto-generated method stub
				super.onProgress(bytesWritten, totalSize);
				int count = (int) ((bytesWritten * 1.0 / totalSize) * 100);  
		        // 下载进度显示  
				progressDialog.setProgress(count);  
			}
			
			
		});
	}
	
	
	public void uploadFile(String url,RequestParams rp,final ProgressDialog progressDialog){
		if(!isNetworkConnected()){
			return;
		}
		client.post(url,rp, new AsyncHttpResponseHandler() {
			@Override
			public void onStart() {
				// TODO Auto-generated method stub
				if(progressDialog!=null&&!progressDialog.isShowing())
				progressDialog.show();
				super.onStart();
			}
			
			@Override
			public void onCancel() {
				// TODO Auto-generated method stub
				super.onCancel();
				if(progressDialog!=null&&progressDialog.isShowing())
				progressDialog.dismiss();
			}
			
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				super.onFinish();
				if(progressDialog!=null&&progressDialog.isShowing())
				progressDialog.dismiss();
			}
			@Override
			public void onSuccess(int statusCode, Header[] arg1, byte[] binaryData) {
				if(statusCode == 200){
					
				}
			}
			
			@Override
			public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
				if(arg0==404){
					Toast.makeText(context, "文件不存在", Toast.LENGTH_SHORT).show();
					return;
				}
				// TODO Auto-generated method stub
				Toast.makeText(context, context.getString(R.string.upload_error), Toast.LENGTH_LONG).show();  
			}
			
			@Override
			public void onProgress(int bytesWritten, int totalSize) {
				// TODO Auto-generated method stub
				super.onProgress(bytesWritten, totalSize);
				int count = (int) ((bytesWritten * 1.0 / totalSize) * 100);  
				if(progressDialog!=null){
					// 下载进度显示  
					progressDialog.setProgress(count);  
				}
			}
		});
	}
}
