package com.testapp.ebay.eBay_Automation;

import org.apache.commons.codec.binary.Base64;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        
        String encodePwd="Atlassian!2!0";
        
        byte[] encode=Base64.encodeBase64(encodePwd.getBytes());
        System.out.println("encoded string"+new String(encode));
        
    }
}
