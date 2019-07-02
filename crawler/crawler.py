# coding=utf-8

import time
from selenium import webdriver
from selenium.webdriver.support.select import Select
from time import sleep
import csv
if __name__ == '__main__':
    driver = webdriver.PhantomJS(executable_path="./webdriver/phantomjs/bin/phantomjs.exe")
    driver.get("http://www.cs.ecitic.com/newsite/yywd/")
    driver.set_window_size(1920, 1080)
    element_province=driver.find_element_by_id("dplProvince")
    select_province=Select(element_province)
    num_province=len(select_province.options)
    element_city=driver.find_element_by_id("dplCity")
    select_city=Select(element_city)
    with open("wangdian.csv","w",encoding="utf8",newline="") as csvfile:
        writer=csv.writer(csvfile)
        for i in range(1,num_province):
            province=select_province.options[i].text
            select_province.select_by_index(i)
            num_city=len(select_city.options)
            for j in range(1,num_city):
                city=select_city.options[j].text
                select_city.select_by_index(j)
                sleep(2)
                span_wangdian=driver.find_elements_by_xpath("//span[@class='BMap_Marker BMap_noprint']")
                driver.find_element_by_xpath("//div[@class='BMap_button BMap_stdMpZoomOut']").click()
                driver.find_element_by_xpath("//div[@class='BMap_button BMap_stdMpZoomOut']").click()
                driver.find_element_by_xpath("//div[@class='BMap_button BMap_stdMpZoomOut']").click()
                num_span=len(span_wangdian)
                for index in range(1,1+num_span):
                    try:
                        driver.find_element_by_xpath("//span[@class='BMap_Marker BMap_noprint'][{}]".format(index)).click()
                    except Exception as e:
                        print(e)
                        continue
                    sleep(3)
                    #driver.save_screenshot("./a.png")
                    #print(driver.page_source)
                    name=driver.find_element_by_xpath("//div[@class='tcBlueBa1']//div/a").text
                    address=driver.find_element_by_xpath("//div[@class='tcBlueBa1']/p[1]").text
                    tel=driver.find_element_by_xpath("//div[@class='tcBlueBa1']/p[2]").text
                    if(name=="" or address=="" or tel==""):
                        continue
                    print("{}  {}  {}  {}  {}".format(province,city,name,address,tel))
                    try:
                        writer.writerow([province,city,name,address[3:],tel[3:]])
                    except Exception as e:
                        print(e)
                        continue

