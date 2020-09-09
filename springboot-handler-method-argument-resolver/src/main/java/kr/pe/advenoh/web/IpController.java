package kr.pe.advenoh.web;

import kr.pe.advenoh.resolver.ClientIp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IpController {
    @GetMapping("/test")
    public String getIpAddress(@ClientIp String clientIp) {
        return String.format("ip address : %s", clientIp);
    }
}
