import java.util.*;

import org.hyperdex.client.Client;
import org.hyperdex.client.ByteString;
import org.hyperdex.client.HyperDexClientException;
import org.hyperdex.client.Iterator;
import org.hyperdex.client.LessEqual;
import org.hyperdex.client.GreaterEqual;
import org.hyperdex.client.Range;
import org.hyperdex.client.Regex;
import org.hyperdex.client.LengthEquals;
import org.hyperdex.client.LengthLessEqual;
import org.hyperdex.client.LengthGreaterEqual;

public class RegexSearch
{
    public static void main(String[] args) throws HyperDexClientException
    {
        Client c = new Client(args[0], Integer.parseInt(args[1]));
        Map<String, Object> attrs0 = new HashMap<String, Object>();
        Object obj0 = c.put("kv", "foo/foo/foo", attrs0);
        assert(obj0 != null);
        Boolean bool0 = (Boolean)obj0;
        assert(bool0 == true);
        Map<String, Object> attrs1 = new HashMap<String, Object>();
        Object obj1 = c.put("kv", "foo/foo/bar", attrs1);
        assert(obj1 != null);
        Boolean bool1 = (Boolean)obj1;
        assert(bool1 == true);
        Map<String, Object> attrs2 = new HashMap<String, Object>();
        Object obj2 = c.put("kv", "foo/foo/baz", attrs2);
        assert(obj2 != null);
        Boolean bool2 = (Boolean)obj2;
        assert(bool2 == true);
        Map<String, Object> attrs3 = new HashMap<String, Object>();
        Object obj3 = c.put("kv", "foo/bar/foo", attrs3);
        assert(obj3 != null);
        Boolean bool3 = (Boolean)obj3;
        assert(bool3 == true);
        Map<String, Object> attrs4 = new HashMap<String, Object>();
        Object obj4 = c.put("kv", "foo/bar/bar", attrs4);
        assert(obj4 != null);
        Boolean bool4 = (Boolean)obj4;
        assert(bool4 == true);
        Map<String, Object> attrs5 = new HashMap<String, Object>();
        Object obj5 = c.put("kv", "foo/bar/baz", attrs5);
        assert(obj5 != null);
        Boolean bool5 = (Boolean)obj5;
        assert(bool5 == true);
        Map<String, Object> attrs6 = new HashMap<String, Object>();
        Object obj6 = c.put("kv", "foo/baz/foo", attrs6);
        assert(obj6 != null);
        Boolean bool6 = (Boolean)obj6;
        assert(bool6 == true);
        Map<String, Object> attrs7 = new HashMap<String, Object>();
        Object obj7 = c.put("kv", "foo/baz/bar", attrs7);
        assert(obj7 != null);
        Boolean bool7 = (Boolean)obj7;
        assert(bool7 == true);
        Map<String, Object> attrs8 = new HashMap<String, Object>();
        Object obj8 = c.put("kv", "foo/baz/baz", attrs8);
        assert(obj8 != null);
        Boolean bool8 = (Boolean)obj8;
        assert(bool8 == true);
        Map<String, Object> attrs9 = new HashMap<String, Object>();
        Object obj9 = c.put("kv", "bar/foo/foo", attrs9);
        assert(obj9 != null);
        Boolean bool9 = (Boolean)obj9;
        assert(bool9 == true);
        Map<String, Object> attrs10 = new HashMap<String, Object>();
        Object obj10 = c.put("kv", "bar/foo/bar", attrs10);
        assert(obj10 != null);
        Boolean bool10 = (Boolean)obj10;
        assert(bool10 == true);
        Map<String, Object> attrs11 = new HashMap<String, Object>();
        Object obj11 = c.put("kv", "bar/foo/baz", attrs11);
        assert(obj11 != null);
        Boolean bool11 = (Boolean)obj11;
        assert(bool11 == true);
        Map<String, Object> attrs12 = new HashMap<String, Object>();
        Object obj12 = c.put("kv", "bar/bar/foo", attrs12);
        assert(obj12 != null);
        Boolean bool12 = (Boolean)obj12;
        assert(bool12 == true);
        Map<String, Object> attrs13 = new HashMap<String, Object>();
        Object obj13 = c.put("kv", "bar/bar/bar", attrs13);
        assert(obj13 != null);
        Boolean bool13 = (Boolean)obj13;
        assert(bool13 == true);
        Map<String, Object> attrs14 = new HashMap<String, Object>();
        Object obj14 = c.put("kv", "bar/bar/baz", attrs14);
        assert(obj14 != null);
        Boolean bool14 = (Boolean)obj14;
        assert(bool14 == true);
        Map<String, Object> attrs15 = new HashMap<String, Object>();
        Object obj15 = c.put("kv", "bar/baz/foo", attrs15);
        assert(obj15 != null);
        Boolean bool15 = (Boolean)obj15;
        assert(bool15 == true);
        Map<String, Object> attrs16 = new HashMap<String, Object>();
        Object obj16 = c.put("kv", "bar/baz/bar", attrs16);
        assert(obj16 != null);
        Boolean bool16 = (Boolean)obj16;
        assert(bool16 == true);
        Map<String, Object> attrs17 = new HashMap<String, Object>();
        Object obj17 = c.put("kv", "bar/baz/baz", attrs17);
        assert(obj17 != null);
        Boolean bool17 = (Boolean)obj17;
        assert(bool17 == true);
        Map<String, Object> attrs18 = new HashMap<String, Object>();
        Object obj18 = c.put("kv", "baz/foo/foo", attrs18);
        assert(obj18 != null);
        Boolean bool18 = (Boolean)obj18;
        assert(bool18 == true);
        Map<String, Object> attrs19 = new HashMap<String, Object>();
        Object obj19 = c.put("kv", "baz/foo/bar", attrs19);
        assert(obj19 != null);
        Boolean bool19 = (Boolean)obj19;
        assert(bool19 == true);
        Map<String, Object> attrs20 = new HashMap<String, Object>();
        Object obj20 = c.put("kv", "baz/foo/baz", attrs20);
        assert(obj20 != null);
        Boolean bool20 = (Boolean)obj20;
        assert(bool20 == true);
        Map<String, Object> attrs21 = new HashMap<String, Object>();
        Object obj21 = c.put("kv", "baz/bar/foo", attrs21);
        assert(obj21 != null);
        Boolean bool21 = (Boolean)obj21;
        assert(bool21 == true);
        Map<String, Object> attrs22 = new HashMap<String, Object>();
        Object obj22 = c.put("kv", "baz/bar/bar", attrs22);
        assert(obj22 != null);
        Boolean bool22 = (Boolean)obj22;
        assert(bool22 == true);
        Map<String, Object> attrs23 = new HashMap<String, Object>();
        Object obj23 = c.put("kv", "baz/bar/baz", attrs23);
        assert(obj23 != null);
        Boolean bool23 = (Boolean)obj23;
        assert(bool23 == true);
        Map<String, Object> attrs24 = new HashMap<String, Object>();
        Object obj24 = c.put("kv", "baz/baz/foo", attrs24);
        assert(obj24 != null);
        Boolean bool24 = (Boolean)obj24;
        assert(bool24 == true);
        Map<String, Object> attrs25 = new HashMap<String, Object>();
        Object obj25 = c.put("kv", "baz/baz/bar", attrs25);
        assert(obj25 != null);
        Boolean bool25 = (Boolean)obj25;
        assert(bool25 == true);
        Map<String, Object> attrs26 = new HashMap<String, Object>();
        Object obj26 = c.put("kv", "baz/baz/baz", attrs26);
        assert(obj26 != null);
        Boolean bool26 = (Boolean)obj26;
        assert(bool26 == true);
        Map<String, Object> checks27 = new HashMap<String, Object>();
        checks27.put("k", new Regex("^foo"));
        Set<Object> X27 = new HashSet<Object>();
        Iterator it27 = c.search("kv", checks27);
        while (it27.hasNext())
        {
            X27.add(it27.next());
        }
        Map<String, Object> checks28 = new HashMap<String, Object>();
        checks28.put("k", new Regex("foo$"));
        Set<Object> X28 = new HashSet<Object>();
        Iterator it28 = c.search("kv", checks28);
        while (it28.hasNext())
        {
            X28.add(it28.next());
        }
        Map<String, Object> checks29 = new HashMap<String, Object>();
        checks29.put("k", new Regex("^b.*/foo/.*$"));
        Set<Object> X29 = new HashSet<Object>();
        Iterator it29 = c.search("kv", checks29);
        while (it29.hasNext())
        {
            X29.add(it29.next());
        }
    }
}
