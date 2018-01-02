0. DESCRIPTION - 简述
    A simple crawler program to crawl news text from the website of China Daily (www.chinadaily.com). 
    简单的中国日报(China Daily, wwww.chinadaily.com)新闻文本爬虫。

    Such program is written to collect a certain scale of English text corpus, which was used for the application of Named Entity Recognition (NER), according to the requirement of a course's final project.
    这个爬虫程序是为了收集一定规模的英文预料用于命名实体识别(Named Entity Recognition, NER)的课程大作业而编写的。

    In the project, besides the source code of the crawler, I also provide an example English news corpus and a labeled dataset of NER.
    在这个项目中，除了爬虫的源代码，笔者还提供了一个英语新闻示例语料和一个标注的命名实体识别数据集。

    Details of the dataset will be introduced later.
    关于标注数据集的细节将在下文说明。

    Because of my limited capacity of programming, it's hard to avoid some some errors and deficiencies in the project. If you find some errors or anything that can be improved, you can contact me via [mengqin_az@foxmail.com]. Thank you very much!
    由于笔者水平有限，难免有疏漏之处，还望大家批评指正！如有关于源代码和数据集的任何问题，可通过邮件[mengqin_az@foxmail.com]联系，谢谢！


1. HOW TO USE - 使用方法
    1) Import the project; - 导入项目;

    2) Reload the jar in the "lib" directory; - 重新加载"lib"文件夹中的jar包;

    3) Set the parameters "timePre" (according to the date you want to crawl the news) in program "src/ChinaDailyCrawler.java"; - (根据待爬取的新闻日期)设置程序"src/ChinaDailyCrawler.java"中变量"timePre"的值;
    (For example, if you want to crawl the news of 2017-12-01, you should set the timePre as "2017-12/02/")
    (例如，当需要爬取2017-12-01的新闻，需要将变量修改为"2017-12/02/")

    4) Run the program "src/ChinaDailyCrawler.java"; - 运行程序 "src/ChinaDailyCrawler.java"; 


2. DESCRIPTION OF FILES - 项目文件说明
    ./lib/ -- The Jars that the program need to load; - 程序需要加载的Jar包;
	./src/ -- The source code of the crawler; - 爬虫源代码;
	./data/China_Daily/ -- Directory where the crawled text is saved; - 保存爬取的文本的文件夹;
    ./data/NER_data/ -- The labeled dataset of NER; - 标注的命名实体识别数据集;
	./data/China_Daily_example.rar  -- The example English news corpus; - 示例英语新闻语料;


3. DESCRIPTION OF THE NER DATASET - 命名实体识别数据集说明 

    The dataset in such project is labeled by using the BRAT annotation tool(http://brat.nlplab.org/), and the result can be directly shown in the web-based GUI of BRAT.
    本项目的数据集使用BRAT标注工具(http://brat.nlplab.org/)进行标注，并可直接使用BRAT的Web界面查看标注结果。

    In the directory of the labeled dataset, annotation.conf is the definition file of the Name Entities, and such project is utilized for the application of the recognition of persons, locations and organizations.
    在标注的数据集文件夹下，annotation.conf是命名实体的定义文件，本项目主要用于人名、地名和组织名的命名实体识别应用.

    The definition of notations is follow.
    符号的定义如下所示。
      PER: Name of person - 人名
      LOC: Name of location - 地名
      ORG: Name of organization - 组织名
      MIST: Other named entity - 其他名字实体

    Every article is relvant ot one ".txt" file and one ".ann" file, where the .txt file is the original text and the ".ann" file is the label information of corresponding text.
    每篇新闻文本与一个".txt"文件和一个".ann"文件对应，其中".txt"文件是新闻的原文， 而".ann"文件是对应文本的标注信息。


