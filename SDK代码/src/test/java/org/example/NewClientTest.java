package org.example;

import org.example.fabric.Delete;
import org.example.fabric.Invoke;
import org.example.fabric.Query;
import org.example.fabric.Update;
import org.junit.Test;

public class NewClientTest {
    @Test
    public void InvokeQuery() throws Exception {
        String[]arg={};
        Invoke.Initial("111","woshichaojidapanghu");
        Invoke.main(arg);
        Query.main(new String[]{"111"});
        Update.Initial("111","new");
        Query.main(new String[]{"111"});
        Delete.main(new String[]{"111"});
        Query.main(new String[]{"111"});
    }
}
