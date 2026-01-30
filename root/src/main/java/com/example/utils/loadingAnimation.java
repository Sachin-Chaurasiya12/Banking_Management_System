/*

   Copyright 2026 Sachin Chaurasiya

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

*/
package com.example.utils;

public class loadingAnimation {
    public void loading() throws InterruptedException {
        Thread.sleep(1000);
        char[] spinner = {'|', '/', '-', '\\'};

        System.out.print("Exiting ");

        for (int i = 0; i < 20; i++) {
            System.out.print("\rExiting " + spinner[i % spinner.length]);
            Thread.sleep(200);
        }
        System.out.println();
    }
    
    public void initconnection() throws InterruptedException{
        Thread.sleep(1000);
         char[] spinner = {'|', '/', '-', '\\'};

        System.out.print("Initializing Connection ");

        for (int i = 0; i < 20; i++) {
            System.out.print("\rConnecting " + spinner[i % spinner.length]);
            Thread.sleep(200);
        }
        System.out.println();
    }

    public void AccountCreation() throws InterruptedException{
        Thread.sleep(1000);
         char[] spinner = {'|', '/', '-', '\\'};

        System.out.print("Creating Account");

        for (int i = 0; i < 20; i++) {
            System.out.print("\rCreating Account " + spinner[i % spinner.length]);
            Thread.sleep(200);
        }
        System.out.println();
    }
}
