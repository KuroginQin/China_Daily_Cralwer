//Program to crawl the news text from www.chinadaily.com.cn
//从www.chinadaily.com.cn(中国日报)上爬取新闻文本的程序

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ChinaDailyCrawler
{
	//Time prefix of of url; url中的时间前缀
	private String timePre = "2017-12/02/";
	//Root page's url 根页面url
	private String rootUrl = "http://www.chinadaily.com.cn/cndy/" + timePre + "index1.html";
	//Prefix of the url of each news page 新闻页面url前缀
	private String newsPageUrlPre = "http://www.chinadaily.com.cn/cndy/" + timePre;
	//Url list of news pages 新闻页面的url列表
	private List<String> newsUrlList = new ArrayList<String>();
	//Counter of the number of news pages 新闻页面计数器
	private int newsPageCount = 0;
	//Counter of the number of news pages crawlled 被爬取的新闻页面计数器
	private int newsPageCrawlCount = 0;
	
	public ChinaDailyCrawler()
	{
		//Extrate the urls of news pages from the root page 
		System.out.println("-Get News URL List-");
		getNewsUrlList(rootUrl);
		System.out.println("Total URLs: " + newsPageCount);
		for(int i=0;i<newsPageCount;i++)
		{
			String curUrl = newsUrlList.get(i);
			System.out.println("URL: " + curUrl);
			String curText = getText(curUrl);
			System.out.println(curText);
			saveText(timePre, newsPageCrawlCount, curText);
			++newsPageCrawlCount;
		}
	}
	
	//Method to get the url list from the root page
	//从根页面获取新闻页面url的方法
	private void getNewsUrlList(String rootUrl)
	{
		StringBuffer stringBuffer = new StringBuffer();
		String str = "";
		try
		{
			java.net.URL url = new java.net.URL(rootUrl); 
			//选择网页的字符集(utf-8/gbk/unicode)
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(),"utf-8"));
			String line;
			//逐行读取HTML文本
			while((line = in.readLine())!=null) 
				stringBuffer.append(line + "\r\n");
			str = stringBuffer.toString();
			//System.out.println(str);
			
			//Extract  the urls of news pages form the HTML text
			//从HTML文本中提取新闻页面的url
			String regex = "content_.*?htm";
			Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
			Matcher match = pattern.matcher(str);
			String curUrl = "";
			while(match.find())
			{
				curUrl = match.group();
				curUrl = newsPageUrlPre + curUrl;
				newsUrlList.add(curUrl);
				++newsPageCount;
				System.out.println(curUrl);
			}
			in.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//Method to extract text from the news pages
	//从新闻页面中提取文本的方法
	private String getText(String newsPageUrl)
	{
		String text = "";
		StringBuffer stringBuffer = new StringBuffer();
		String str = "";
		try
		{
			java.net.URL url = new java.net.URL(newsPageUrl); 
			//Select the Character set 选择网页的字符集(utf-8/gbk/unicode)
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(),"utf-8"));
			String line;
			//Read the HTML text line-by-line 逐行读取HTML文本
			while((line = in.readLine())!=null) 
				stringBuffer.append(line + "\r\n");
			str = stringBuffer.toString();
			
			//Extract text from the news page 从新闻页面中提取文本
			//Remove the redundant HTML tags 删除冗余的HTML标签
			str = str.replaceAll("(?is)<!DOCTYPE.*?>", "");
			str = str.replaceAll("(?is)<!--.*?-->", "");
			str = str.replaceAll("(?is)<script.*?>.*?</script>", "");
			str = str.replaceAll("(?is)<style.*?>.*?</style>", "");
			//Use Jsoup to extract specific content 使用Jsoup提取特定内容
			Document doc = Jsoup.parse(str);
			String title = "";
			String content = "";
			title = doc.select("[class=lft_art]>h1").first().text();
			//System.out.println("title: " + title);
			content = doc.select("[id=Content]").first().text();
			//System.out.println("content: " + content);
			text = title + "\r\n" + content;
			//System.out.println("Text: " + text);
			
			in.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return text;
	}
	
	//Method to save text 将文本保存至本地的方法
	private void saveText(String timePre, int textIndex, String text)
	{
		FileWriter fileWriter=null;
		try
		{
			timePre = timePre.replace("/", ".");
			fileWriter= new FileWriter(".//data//China_Daily//China_Daily_" + timePre+"_" + textIndex + ".txt"); 
			fileWriter.write(text);
			fileWriter.flush();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//System.out.println(e.getMessage());
		}
		finally
		{
			try
			{
				fileWriter.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String args[])
	{
		new ChinaDailyCrawler();
	}
}