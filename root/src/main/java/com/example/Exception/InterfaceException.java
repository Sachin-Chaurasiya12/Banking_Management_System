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
package com.example.Exception;

import java.io.IOException;

import com.example.utils.printInsider;

public class InterfaceException {
    static printInsider in = new printInsider();
    public Exception InvalidInput() throws Exception{
        throw new IOException("Invalid input! try again");
    }
    public Exception valueError() throws Exception{
        throw new Exception("Value is not defined! Enter the Required input");
    }
    
}
