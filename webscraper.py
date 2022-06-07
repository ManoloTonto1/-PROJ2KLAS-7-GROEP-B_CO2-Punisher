
from selenium.webdriver.common.keys import Keys
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.common.exceptions import TimeoutException
import json
import os

file = open("file.tmp", "r").read()
loadedInfo = json.loads(file)

url = "https://www.google.com/maps/dir///@52.0587545,4.3305252,14z/data=!4m2!4m1!3e4"
# browser = webdriver.Firefox()

# options = webdriver.FirefoxOptions()
# options.add_argument('--headless')
# browser = webdriver.Firefox(options=options)
options = webdriver.ChromeOptions()
options.add_argument('--headless')
browser = webdriver.Chrome(options=options)

browser.get(url)


def getDistanceByCar():

    try:
        element = WebDriverWait(browser, 10).until(
            EC.presence_of_element_located(
                (By.XPATH, '/html/body/div[3]/div[9]/div[3]/div[1]/div[2]/div/div[3]/div[1]/div[1]/div[2]/div[1]/div/input'))
        )
    finally:
        element.send_keys(loadedInfo["start"])
        # browser.quit()
    try:
        element2 = WebDriverWait(browser, 10).until(
            EC.presence_of_element_located(
                (By.XPATH, '/html/body/div[3]/div[9]/div[3]/div[1]/div[2]/div/div[3]/div[1]/div[2]/div[2]/div[1]/div/input'))
        )
    finally:
        element2.send_keys(loadedInfo["end"])
        element2.send_keys(Keys.ENTER)

    browser.find_element_by_xpath(
        '/html/body/div[3]/div[9]/div[3]/div[1]/div[2]/div/div[2]/div/div/div/div[2]/button').click()
    try:
        scrapedtext = WebDriverWait(browser, 10).until(
            EC.presence_of_element_located(
                (By.XPATH, '/html/body/div[3]/div[9]/div[9]/div/div/div[1]/div[2]/div/div[1]/div/div/div[4]/div/div[1]/div[1]/div[1]/div[2]/div'))
        )
    finally:
        finaltext = scrapedtext.text
        print(finaltext)
    return finaltext


def getDistanceByPublicTransport():

    try:
        element = WebDriverWait(browser, 10).until(
            EC.presence_of_element_located(
                (By.XPATH, '/html/body/div[3]/div[9]/div[3]/div[1]/div[2]/div/div[3]/div[1]/div[1]/div[2]/div[1]/div/input'))
        )
    finally:
        element.send_keys(loadedInfo["start"])
        # browser.quit()
    try:
        element2 = WebDriverWait(browser, 10).until(
            EC.presence_of_element_located(
                (By.XPATH, '/html/body/div[3]/div[9]/div[3]/div[1]/div[2]/div/div[3]/div[1]/div[2]/div[2]/div[1]/div/input'))
        )
    finally:
        element2.send_keys(loadedInfo["end"])
        element2.send_keys(Keys.ENTER)

    browser.find_element_by_xpath(
        '/html/body/div[3]/div[9]/div[3]/div[1]/div[2]/div/div[2]/div/div/div/div[2]/button').click()
    try:
        scrapedtext = WebDriverWait(browser, 10).until(
            EC.presence_of_element_located(
                (By.XPATH, '/html/body/div[3]/div[9]/div[9]/div/div/div[1]/div[2]/div/div[1]/div/div/div[4]/div/div[1]/div[1]/div[1]/div[2]/div'))
        )
    finally:
        finaltext = scrapedtext.text
        print(finaltext)
    return finaltext


def getDistanceByWalk():

    try:
        element = WebDriverWait(browser, 10).until(
            EC.presence_of_element_located(
                (By.XPATH, '/html/body/div[3]/div[9]/div[3]/div[1]/div[2]/div/div[3]/div[1]/div[1]/div[2]/div[1]/div/input'))
        )
    finally:
        element.send_keys(loadedInfo["start"])
        # browser.quit()
    try:
        element2 = WebDriverWait(browser, 10).until(
            EC.presence_of_element_located(
                (By.XPATH, '/html/body/div[3]/div[9]/div[3]/div[1]/div[2]/div/div[3]/div[1]/div[2]/div[2]/div[1]/div/input'))
        )
    finally:
        element2.send_keys(loadedInfo["end"])
        element2.send_keys(Keys.ENTER)

    browser.find_element_by_xpath(
        '/html/body/div[3]/div[9]/div[3]/div[1]/div[2]/div/div[2]/div/div/div/div[4]/button').click()
    try:
        scrapedtext = WebDriverWait(browser, 10).until(
            EC.presence_of_element_located(
                (By.XPATH, '/html/body/div[3]/div[9]/div[9]/div/div/div[1]/div[2]/div/div[1]/div/div/div[4]/div[1]/div[1]/div[3]/div[1]/div[2]'))
        )
    except TimeoutException:
        try:
            scrapedtext = WebDriverWait(browser, 10).until(
                EC.presence_of_element_located(
                    (By.XPATH, '/html/body/div[3]/div[9]/div[9]/div/div/div[1]/div[2]/div/div[1]/div/div/div[4]/div/div[1]/div[3]/div[1]/div[2]'))
            )
        finally:
            finaltext = scrapedtext.text
            print(finaltext)
        return finaltext
    finally:
        finaltext = scrapedtext.text
        print(finaltext)
    return finaltext


def getDistanceByBike():

    try:
        element = WebDriverWait(browser, 10).until(
            EC.presence_of_element_located(
                (By.XPATH, '/html/body/div[3]/div[9]/div[3]/div[1]/div[2]/div/div[3]/div[1]/div[1]/div[2]/div[1]/div/input'))
        )
    finally:
        element.send_keys(loadedInfo["start"])
        # browser.quit()
    try:
        element2 = WebDriverWait(browser, 10).until(
            EC.presence_of_element_located(
                (By.XPATH, '/html/body/div[3]/div[9]/div[3]/div[1]/div[2]/div/div[3]/div[1]/div[2]/div[2]/div[1]/div/input'))
        )
    finally:
        element2.send_keys(loadedInfo["end"])
        element2.send_keys(Keys.ENTER)

    browser.find_element_by_xpath(
        '/html/body/div[3]/div[9]/div[3]/div[1]/div[2]/div/div[2]/div/div/div/div[5]/button').click()
    try:
        scrapedtext = WebDriverWait(browser, 10).until(
            EC.presence_of_element_located(
                (By.XPATH, '/html/body/div[3]/div[9]/div[9]/div/div/div[1]/div[2]/div/div[1]/div/div/div[4]/div/div[1]/div[1]/div[1]/div[2]/div'))
        )
    except TimeoutException:
        try:
            scrapedtext = WebDriverWait(browser, 10).until(
                EC.presence_of_element_located(
                    (By.XPATH, '/html/body/div[3]/div[9]/div[9]/div/div/div[1]/div[2]/div/div[1]/div/div/div[4]/div/div[1]/div[3]/div[1]/div[2]'))
            )
        finally:
            finaltext = scrapedtext.text
            print(finaltext)
        return finaltext
    finally:
        finaltext = scrapedtext.text
        print(finaltext)
    return finaltext

def writeFile(result):
    f = open("result.txt", "w")
    f.write(result)
    f.close()
    os.remove("file.tmp")
    donefile = open(".done", "w")
    donefile.close()

if loadedInfo["type"] == "1":
    writeFile(getDistanceByWalk())
elif loadedInfo["type"] == "2":
    writeFile(getDistanceByBike())
else:
    writeFile(getDistanceByCar())

