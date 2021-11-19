/*
SPDX-License-Identifier: Apache-2.0
*/

package org.example;

import org.example.fabric.ClientApp;
import org.example.fabric.EnrollAdmin;
import org.example.fabric.RegisterUser;
import org.junit.Test;

public class ClientTest {

	@Test
	public void testFabCar() throws Exception {
		EnrollAdmin.main(null);
		RegisterUser.main(null);
		ClientApp.main(null);
	}
}
