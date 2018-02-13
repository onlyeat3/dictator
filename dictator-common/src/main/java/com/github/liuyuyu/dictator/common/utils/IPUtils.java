package com.github.liuyuyu.dictator.common.utils;

import com.github.liuyuyu.dictator.common.exception.NetworkGetFailException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author liuyuyu
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IPUtils {
    public static String getIP() {
        Enumeration<?> netInterfaces;
        List<NetworkInterface> netlist = new ArrayList<>();
        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();//获取当前环境下的所有网卡
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
                if (ni.isLoopback())
                    continue;//过滤 lo网卡
                netlist.add(0, ni);//倒置网卡顺序
            }  /*
                  用上述方法获取所有网卡时，得到的顺序与服务器中用ifconfig命令看到的网卡顺序相反，
                  因此，想要从第一块网卡开始遍历时，需要将Enumeration<?>中的元素倒序 */

            for (NetworkInterface list : netlist) { //遍历每个网卡

                Enumeration<?> cardipaddress = list.getInetAddresses();//获取网卡下所有ip

                while (cardipaddress.hasMoreElements()) {//将网卡下所有ip地址取出
                    InetAddress ip = (InetAddress) cardipaddress.nextElement();
                    if (!ip.isLoopbackAddress()) {
                        if (ip.getHostAddress().equalsIgnoreCase("127.0.0.1")) {//guo
                            //return ip.getHostAddress();
                            continue;
                        }
                        if (ip instanceof Inet6Address) {   //过滤ipv6地址  add by liming 2013-9-3
                            //return ip.getHostAddress();
                            continue;
                        }
                        if (ip instanceof Inet4Address) {    //返回ipv4地址
                            return ip.getHostAddress();
                        }
                    }
                    return InetAddress.getLocalHost().getHostAddress();//默认返回
                }
            }
            throw NetworkGetFailException.from("Unable to get network card information");
        } catch (Exception e) {
            throw NetworkGetFailException.from(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(IPUtils.getIP());
    }
}
