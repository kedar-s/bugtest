package org.tolven.core.bean;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class TextConverter {

	private boolean body_found = false;
	private boolean in_body = false;
	private boolean center = false;
	private boolean pre = false;
	private String href = "";
    private String body;
    
	public String convert(String source) throws Exception
	{
		StringBuffer result = new StringBuffer();
		StringBuffer result2 = new StringBuffer();
		StringReader input = new StringReader(source);
		try
		{
			String text = null;
			int c = input.read();
			while (c != -1) // Convert until EOF
			{
				text = "";
				if (c == '<') // It's a tag!!
				{
					String CurrentTag = getTag(input); // Get the rest of the tag
					text = convertTag(CurrentTag);
				}
				else if (c == '&')
				{
					String specialchar = getSpecial(input);
					if (specialchar.equals("lt;") || specialchar.equals("#60"))
						text = "<";
					else if (specialchar.equals("gt;") || specialchar.equals("#62"))
						text = ">";
					else if (specialchar.equals("amp;") || specialchar.equals("#38"))
						text = "&";
					else if (specialchar.equals("nbsp;"))
						text = " ";
					else if (specialchar.equals("quot;") || specialchar.equals("#34"))
						text = " ";
					else if (specialchar.equals("copy;") || specialchar.equals("#169"))
						text = "[Copyright]";
					else if (specialchar.equals("reg;") || specialchar.equals("#174"))
						text = "[Registered]";
					else if (specialchar.equals("trade;") || specialchar.equals("#153"))
						text = "[Trademark]";
					else
						text = "&" + specialchar;
				}
				else if (!pre && Character.isWhitespace((char)c))
				{
					StringBuffer s = in_body ? result : result2;
					if (s.length() > 0 && Character.isWhitespace(s.charAt(s.length()-1)))
						text = "";
					else text = " ";
				}
				else
				{
					text = "" + (char)c;
				}
				StringBuffer s = in_body ? result : result2;
				s.append(text);
				c = input.read();
			}
		}
		catch (Exception e)
		{
			input.close();
			throw e;
		}
		StringBuffer s = body_found ? result : result2;
		return s.toString().trim();
	}
	String getTag(Reader r) throws IOException
	{
		StringBuffer result = new StringBuffer();
		int level = 1;
		result.append('<');
		while (level > 0)
		{
			int c = r.read();
			if (c == -1) break; // EOF
			result.append((char)c);
			if (c == '<') level++; else if (c == '>') level--;
		}
		return result.toString();
	}
	String getSpecial(Reader r) throws IOException
	{
		StringBuffer result = new StringBuffer();
		r.mark(1);//Mark the present position in the stream
		int c = r.read();
		while (Character.isLetter((char)c))
		{
			result.append((char)c);
			r.mark(1);
			c = r.read();
		}
		if (c == ';') result.append(';');
		else r.reset();
		return result.toString();
	}
	boolean isTag(String s1, String s2)
	{
		s1 = s1.toLowerCase();
		String t1 = "<" + s2.toLowerCase() + ">";
		String t2 = "<" + s2.toLowerCase() + " ";
		return s1.startsWith(t1) || s1.startsWith(t2);
	}
	String convertTag(String t) throws IOException
	{
		String result = "";
		if (isTag(t,"body"))
		{ in_body = true; body_found = true; }
		else if (isTag(t,"/body"))
		{ in_body = false; result = " "; }
		else if (isTag(t,"center"))
		{ result = " "; center = true; }
		else if (isTag(t,"/center"))
		{ result = " "; center = false; }
		else if (isTag(t,"pre"))
		{ result = " "; pre = true; }
		else if (isTag(t,"/pre"))
		{ result = " "; pre = false; }
		else if (isTag(t,"p"))
			result = "\n ";
		else if (isTag(t,"br"))
			result = "\n";
		else if (isTag(t,"h1") || isTag(t,"h2") ||
			isTag(t,"h3") ||isTag(t,"h4") || isTag(t,"h5") || isTag(t,"h6") ||
			isTag(t,"h7"))
			result = " \n";
		else if (isTag(t,"/h1") || isTag(t,"/h2") ||
			isTag(t,"/h3") ||isTag(t,"/h4") || isTag(t,"/h5") || isTag(t,"/h6") ||
			isTag(t,"/h7"))
			result = " ";
		else if (isTag(t,"dl"))
			result = " \n";
		else if (isTag(t,"/dl"))
			result = " ";
		else if (isTag(t,"dd"))
			result = " * ";
		else if (isTag(t,"dt"))
			result = " ";
		else if (isTag(t,"li"))
			result = "  * ";
		else if (isTag(t,"/ul"))
			result = " ";
		else if (isTag(t,"/ol"))
			result = " ";
		else if (isTag(t,"hr"))
			result = "\n____________________________________________________________________________________________\n";
		else if (isTag(t,"table"))
			result = " ";
		else if (isTag(t,"/table"))
			result = " ";
		else if (isTag(t,"form"))
			result = " ";
		else if (isTag(t,"/form"))
			result = " ";
		else if (isTag(t,"b"))
			result = "*";
		else if (isTag(t,"/b"))
			result = "*";
		else if (isTag(t,"i"))
			result = " ";
		else if (isTag(t,"/i"))
			result = " " ;
		else if (isTag(t,"img"))
		{
			int idx = t.indexOf("alt=");
			if (idx != -1)
			{
				idx += 5;
				int idx2 = t.indexOf("\"",idx);
				result = t.substring(idx,idx2);
			}
		}
		else if (isTag(t,"a"))
		{
			int idx = t.indexOf("href=");
			if (idx != -1)
			{
				idx += 6;
				int idx2 = t.indexOf("\"",idx);
				href = t.substring(idx,idx2);
			}
			else
			{
				href = "";
			}
		}
		else if (isTag(t,"/a"))
		{
			if (href.length() > 0)
			{
				result = " [ " + href + " ]";
				href = "";
			}
		}
		return result;
	}
	public String html2text(String a) throws Exception
	{
		
		String converted;
		String path = a;
		String s = null;
		byte buf[] = path.getBytes();
		s = new String(buf);
		TextConverter h = new TextConverter();
		converted = h.convert(s);
        return converted;
	}
}
