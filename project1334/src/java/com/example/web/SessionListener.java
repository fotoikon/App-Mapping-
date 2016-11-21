/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.web;

/**
 *
 * @author fotini
 */

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
    
    private static int num_of_sessions;

    public static int getAllSession() {
	return num_of_sessions;
    }

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        num_of_sessions ++;
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        num_of_sessions --;
    }
}
    

