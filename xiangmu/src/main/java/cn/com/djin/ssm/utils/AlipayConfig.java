package cn.com.djin.ssm.utils;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：  ksfxhw3818@sandbox.com   111111
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016101500690815";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDwNA+mNJl/0/Pg8hoPSSMNqpR7E1ybq4mhRGTLoy37epPFh8w/tDzDHYJD727p4SwRn7l6OOL2otx6rcmByotDpGWZhOxG8vYMdL6qUDCHazWt2VToRBHQt37qdSH+3rNU5OfsNYBqEd04yEXN+g9gGKYpsTzikU8fWs9Qj+35fLROCcvCN3AscemQBkzsbtwX0dU2GKJOf5jK/hFq0ENm1ATD3Vqv1rE4ETpJoiOgE0avWXvXpg9uJCKxsWkqZniMEaQnWBBjfDXoT3G4XLZYX148Y8lMf0jDWTv4Lgq86jii0YqXn81IJS+MYeywIN3+Mw9vjTIXuSI5cW+RuXavAgMBAAECggEAVEuA3mwBHlnscRmlSY36Dja5qsNtsXeucfjFgq0+Ws12hFCWvhhrZnG5oEurWcpao8iw15KVG8alpyvL1o+U9eUAfrKmFHP5Iv/pVW2UykE2ck8du/D/a4aptG/swTu4JWe7eCqCaTbhsqad0J4bvTaJQYJggoZKBQ/iRzxrYCzUL1CQxNnTyAbuii/rPc0W+Ol89Q0mTk+WQWNiooftfrXRMiNSeYG9v0xLwzwzOxxZKH2d4B03qereSMJBV4jjRyxOpN++LPGsEoB9SQMWejLo+7Wsoy94MzrJ5oQULWG1FcH0SkaM2OFeAQBwoX505ypEnJnYvVJ9ZDzv9tAH8QKBgQD6cI3Ud49ba4WiztA3pK65DwLcPs6uug9fPcsT7qxuQKWeMAnc7JDK2oo3xMw+NvCApeVmzJdcI368rwHauVk9H+XQvu+iiFdF7zNwcJ70sy4y9qI+Imy0osJBRWa69m0JWDPdN831GUqe/O9Bxqw8AxIdrOWuubS4n5UQdJiTmQKBgQD1iVN+N0sTQKdH2B/thhR6dOVNd0VfaTq2UXxdbFmd7sSf5vULTjIVgRzJbCigh3Mz3hQpujlBnwM1JmBqGfura9HSv8Ur4BxBTgVbH5lXfIx8LR2xT8K7dMi+gpT4StY/yEW+EXADcbFK40fzhQgOrbjtrUFwV7bu8PqW+LVJhwKBgCeW01Ta8ZaKEL4D43ZoWhZ3AQTACPwl/XgY1Jl6CVgEcCnpfiWmX90xXtgiDAdpt+seTn1JqkOueMJrB+gDlM/M0w+En20aEHHFtmP36ajVCmCUDGG2H2kOsZaP2gWEHwhzIP+/apdsQBeR9GB5f5CdC6gVRNmyEZd94pqPU+9BAoGAYNr28IbnOoT6d8Ctd4i4c0XBXjl6SBS+cETchg3DnCUfsuPsLbV+1DTGIj5kGE7WBtPHqF4pT9Vl8oVc6v2s5QX3EudK4+/XtXsNadChMH2U/Tbey57m9gMvXNct5HPA6BAcUsmWIgWrJhtZupnzf7bPiBIcCjDPnJm6f+tDt+kCgYEAn0N/zcIdFFiDDZxJlEOkJD1nxIVG2zcfqLkifviaSpKUzUTN4QaEnYeNKXM+7o51sZ98mj8J2EI6fb/WndhhA4qH5cqdDnQYg01SgHq3yAJZlYGhUPz2PBhJ+YuErxRbH3nlBGf635XuFsWHfHaejLpnwRLZC3Q51QCbtTF0Tuc=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA8DQPpjSZf9Pz4PIaD0kjDaqUexNcm6uJoURky6Mt+3qTxYfMP7Q8wx2CQ+9u6eEsEZ+5ejji9qLceq3JgcqLQ6RlmYTsRvL2DHS+qlAwh2s1rdlU6EQR0Ld+6nUh/t6zVOTn7DWAahHdOMhFzfoPYBimKbE84pFPH1rPUI/t+Xy0TgnLwjdwLHHpkAZM7G7cF9HVNhiiTn+Yyv4RatBDZtQEw91ar9axOBE6SaIjoBNGr1l716YPbiQisbFpKmZ4jBGkJ1gQY3w16E9xuFy2WF9ePGPJTH9Iw1k7+C4KvOo4otGKl5/NSCUvjGHssCDd/jMPb40yF7kiOXFvkbl2rwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/orders/myOrdersPay";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

