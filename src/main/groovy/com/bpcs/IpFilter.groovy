package com.bpcs

import org.apache.commons.lang.StringUtils
import java.util.logging.Logger
import java.util.logging.Level


/**
 * Created by wpfeiffe on 5/8/2015.
 */
public class IpFilter
{
    private static final Logger logger = Logger.getLogger(IpFilter.class.getName());

    static void main(String[] args)
    {
        if (args.size() == 2)
        {

            if (netMatch(args[0], args[1]))
            {
                println "The ip matches the mask"
            }
            else
            {
                println "The ip does NOT match the mask"
            }
        }
        else
        {
            println "Wrong number of arguments"
        }
    }

    public boolean netMatch(String subnet, String addressToTest)
    {

        String[] parts = subnet.split("/");
        String ip = StringUtils.trimToEmpty(parts[0]);
        int prefix;

        if (parts.length < 2)
        {
            prefix = 0;
        }
        else
        {
            prefix = Integer.parseInt(StringUtils.trimToEmpty(parts[1]));
        }

        Inet4Address a = null;
        Inet4Address a1 = null;
        try
        {
            a = (Inet4Address) InetAddress.getByName(ip);
            a1 = (Inet4Address) InetAddress.getByName(addressToTest);
        }
        catch (UnknownHostException e)
        {
            logger.log(Level.SEVERE, "Problem interpreting ip addresses: " + ip + ", " + addressToTest, e);
            return false;   // fail the test
        }

        byte[] b = a.getAddress();
        int ipInt = ((b[0] & 0xFF) << 24) |
                ((b[1] & 0xFF) << 16) |
                ((b[2] & 0xFF) << 8) |
                ((b[3] & 0xFF) << 0);

        byte[] b1 = a1.getAddress();
        int ipInt1 = ((b1[0] & 0xFF) << 24) |
                ((b1[1] & 0xFF) << 16) |
                ((b1[2] & 0xFF) << 8) |
                ((b1[3] & 0xFF) << 0);

        int mask = ~((1 << (32 - prefix)) - 1);

        if ((ipInt & mask) == (ipInt1 & mask))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
