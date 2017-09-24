package com.fastdeliveryservice.utility;

/**
 * Created by Martina
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

@Component
public class HostInfoContributor implements InfoContributor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    public HostInfoContributor(){}

    @Override
    public void contribute(Info.Builder builder) {
        InetAddress ip = InetAddress.getLoopbackAddress();
        Map<String, String> hostMap = new HashMap<>();

        try {
            ip = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            logger.info(String.valueOf(e));
        }
        hostMap.put("ipAddress", ip.getHostAddress());
        hostMap.put("hostname", ip.getHostName());
        builder.withDetail("appHostInfo", hostMap);

        hostMap = new HashMap<>();
    }

}
