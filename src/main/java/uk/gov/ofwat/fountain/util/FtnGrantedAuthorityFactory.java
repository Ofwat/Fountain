package uk.gov.ofwat.fountain.util;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import waffle.spring.GrantedAuthorityFactory;
import waffle.windows.auth.WindowsAccount;


public class FtnGrantedAuthorityFactory implements GrantedAuthorityFactory{

    /** The prefix. */
    private final String  prefix;

    /** The convert to upper case. */
    //private final boolean convertToUpperCase;	
	
    /**
     * Instantiates a new fqn granted authority factory.
     *
     * @param newPrefix
     *            the new prefix
     * @param newConvertToUpperCase
     *            the new convert to upper case
     */
    public FtnGrantedAuthorityFactory(final String newPrefix) {
        this.prefix = newPrefix;
        //this.convertToUpperCase = newConvertToUpperCase;
    }    
    
	@Override
	public GrantedAuthority createGrantedAuthority(final WindowsAccount windowsAccount) {
		
		//String grantedAuthorityString = windowsAccount.getFqn();
		String grantedAuthorityString = windowsAccount.getName();
		
        if (this.prefix != null) {
            grantedAuthorityString = this.prefix + grantedAuthorityString;
        }

        System.out.println("***" + grantedAuthorityString + "***");
        
        //if (this.convertToUpperCase) {
        //    grantedAuthorityString = grantedAuthorityString.toUpperCase();
        //}

        return new SimpleGrantedAuthority(grantedAuthorityString);	
   }

}
