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
package com.example.interfaceModel;

import com.example.utils.printInsider;

public class modelUI {
    static final printInsider in = new printInsider();
    static final String Version = "v1.0.0";
    public void version(){
        in.println("+-----------------------------------------------------------------+");
        in.println("            Banking Management System Version : " + Version + "   ");
        in.println("+-----------------------------------------------------------------+");
    }
    public void interfaceModel(){
        in.println("+--------------------------------+--------------------------------+");
        in.print("1) Account");
        in.print("        2) Check Balance");
        in.println("        3) Deposit");
        in.print("4) Withdraw");
        in.print("       5) Transaction");
        in.println("          6) Other Operation");
        in.println("7) Exit");
        in.println("+--------------------------------+--------------------------------+");
    }
    public void otherOperation(){
        in.println("+---------------------------+");
        in.println("1) Account logs");
        in.println("2) Transaction logs");
        in.println("0) <-- Back");
        in.println("+---------------------------+");
    }
}
