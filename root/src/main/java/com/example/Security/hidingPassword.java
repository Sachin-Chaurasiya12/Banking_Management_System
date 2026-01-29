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
package com.example.Security;

import java.io.Console;
import java.util.Scanner;

public class hidingPassword {
    public String passwordhide(){
        Console console = System.console();
        if (console != null) {
            char[] pwd = console.readPassword("Enter password: ");
            return new String(pwd);
        }else {
            System.out.print("Enter password: ");
            try (Scanner sc = new Scanner(System.in)) {
                return sc.nextLine();
            }
        }
    }
    public String passwordhiderenter(){
        Console console = System.console();
        if (console != null) {
            char[] pwd = console.readPassword("re Enter password: ");
            return new String(pwd);
        }else {
            System.out.print("Enter password: ");
            try (Scanner sc = new Scanner(System.in)) {
                return sc.nextLine();
            }
        }
    }
}
