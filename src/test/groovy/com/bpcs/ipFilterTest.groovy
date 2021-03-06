package com.bpcs

import spock.lang.Specification

/**
 * Created by wpfeiffe on 5/9/15.
 */
class ipFilterTest extends Specification{
    def "simple positive test"() {
        setup:
        IpFilter ipFilter = new IpFilter()
        when:
        String mask = "10.10.4.0/24"
        String ip = "10.10.4.5"
        then:
        ipFilter.netMatch(mask, ip) == true
    }
        def "complex positive tests"() {

        setup:
            IpFilter ipFilter = new IpFilter()
        expect:
            ipFilter.netMatch(a, b) == c
        where:
            a | b | c
            "204.94.39.0/24"   | "204.94.39.1"     | true
            "204.94.39.0/24"   | "204.94.39.254"   | true
            "205.166.218.0/25" | "205.166.218.1"   | true
            "205.166.218.0/25" | "205.166.218.127" | true
            "205.166.218.0/25" | "205.166.218.128" | false
            "205.166.218.0/25" | "205.166.218.254" | false
            "12.34.246.0/25"   | "12.34.246.1"     | true
            "12.34.246.0/25"   | "12.34.246.127"   | true
            "12.34.246.0/25"   | "12.34.246.128"   | false
            "12.34.246.0/25"   | "12.34.246.254"   | false
            "205.242.229.0/25" | "205.242.229.1"   | true
            "205.242.229.0/25" | "205.242.229.127" | true
            "205.242.229.0/25" | "205.242.229.128" | false
            "205.242.229.0/25" | "205.242.229.254" | false
            "204.118.102.0/25" | "204.118.102.1"   | true
            "204.118.102.0/25" | "204.118.102.127" | true
            "204.118.102.0/25" | "204.118.102.128" | false
            "204.118.102.0/25" | "204.118.102.254" | false


    }
}
