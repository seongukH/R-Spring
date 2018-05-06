package com.hsucomp.rjava;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



import com.github.rcaller.rStuff.RCaller;
import com.github.rcaller.rStuff.RCode;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		  try {
		         
		         RCaller caller = new RCaller();
		         caller.setRscriptExecutable("Rscript.exe ¿« ∞Ê∑Œ ∫πªÁ«œ±‚.");
		         RCode code = new RCode();
		         code.clear();
		         
		         File file;
		         file = code.startPlot();
		         System.out.println(file);
		         code.addRCode("x=c(1,4,3,5,6,10)");
		         code.addRCode("plot(x)");
		         code.endPlot();
		         
		         caller.setRCode(code);
		         caller.runOnly();
		         code.showPlot(file);
		         
		         logger.info("123 {}.", code);
		         
		      } catch (Exception e) {
		         // TODO: handle exception
		    	  logger.info("exception");
		      }
		
		return "home";
	}
	
	@RequestMapping(value = "/caller", method = RequestMethod.GET)
	public void test(Model model) {
		  try {
		         
		         RCaller caller = new RCaller();
		         caller.setRscriptExecutable("D:\\R-3.4.4\\bin\\x64\\Rscript.exe");
		         RCode code = new RCode();
		         code.clear();
		         
		         File file;
		         file = code.startPlot();
		         
		         System.out.println(file);
		         model.addAttribute("chart", file);
		         
		         code.addRCode("x=c(1,4,3,5,6,10)");
		         code.addRCode("plot(x)");
		         code.endPlot();
		         
		         caller.setRCode(code);
		         caller.runOnly();
		         code.showPlot(file);
		         
		      } catch (Exception e) {
		         // TODO: handle exception
		    	  logger.info("exception");
		      }
		
		  
	}
	
	@RequestMapping(value = "/caller3", method = RequestMethod.GET)
	public ResponseEntity<byte[]> displayFile(String filename, Model model)throws Exception {
		InputStream in = null;  
		ResponseEntity<byte[]> entity = null;
		
		
		try {
		         
		         RCaller caller = new RCaller();
		         caller.setRscriptExecutable("D:\\R-3.4.4\\bin\\x64\\Rscript.exe");
		         RCode code = new RCode();
		         code.clear();
		         
		         File file;
		         file = code.startPlot();
		         
		         System.out.println(file);
		         
		         HttpHeaders headers = new HttpHeaders();
		         in = new FileInputStream(file);
		         entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		         model.addAttribute("chart", entity);
		         
		         code.addRCode("x=c(1,4,3,5,6,10)");
		         code.addRCode("plot(x)");
		         code.endPlot();
		         
		         caller.setRCode(code);
		         caller.runOnly();
		         code.showPlot(file);
		         
		      } catch (Exception e) {
		         // TODO: handle exception
		    	  logger.info("exception");
		      }
		
		  return entity;
	}
	
	
	@RequestMapping(value = "/caller2", method = RequestMethod.GET)
	public void test2(Model model) {
		  try {
		         
		         RCaller caller = new RCaller();
		         caller.setRscriptExecutable("D:\\R-3.4.4\\bin\\x64\\Rscript.exe");
		         RCode code = new RCode();
		         code.clear();
		         
		         File file;
		         file = code.startPlot();
		         
		         System.out.println(file);
		         model.addAttribute("chart", file);
		         
		         code.addRCode("library(KoNLP)\n" + 
		         		"library(rvest)\n" + 
		         		"library(xml2)\n" + 
		         		"library(wordcloud)\n" + 
		         		"\n" + 
		         		"useSejongDic()\n" + 
		         		"\n" + 
		         		"\n" + 
		         		"\n" + 
		         		"url <- \"https://news.sbs.co.kr/news/endPage.do?news_id=N1004700839&amp;plink=PLUS&amp;cooper=SBSNEWSMAIN\"\n" + 
		         		"news <- read_html(url);\n" + 
		         		"\n" + 
		         		"nnews <- html_nodes(news, css='.main_text'); #Í∏∞ÏÇ¨ Î≥∏Î¨∏ css id\n" + 
		         		"\n" + 
		         		"nnews <- gsub(\"<br>\", \"\", nnews)#Í∏∞ÏÇ¨ Î≥∏Î¨∏?óê?Ñú html?ÖåÍ∑? br ?†úÍ±?\n" + 
		         		"\n" + 
		         		"enn <- extractNoun(nnews)#Î™ÖÏÇ¨ Ï∂îÏ∂ú.\n" + 
		         		"\n" + 
		         		"#enn\n" + 
		         		"\n" + 
		         		"#sort(table(enn))#?Öå?ù¥Î∏îÌôî ?õÑ ?ã®?ñ¥ ÎπàÎèÑ?àòÎ≥ÑÎ°ú ?†ï?†¨.\n" + 
		         		"\n" + 
		         		"wordcount = table(unlist(enn))\n" + 
		         		"df_word = as.data.frame(wordcount, stringsAsFactors = F)\n" + 
		         		"#sort(df_word)\n" + 
		         		"\n" + 
		         		"wordcloud(words = df_word$Var1, freq = df_word$Freq, min.freq = 2,\n" + 
		         		"          max.words = 200, random.order = F, rot.per = .1, scale = c(4,0,3),\n" + 
		         		"          colors = brewer.pal(8, \"Dark2\"))");
		         code.addRCode("");
		         code.endPlot();
		         
		         caller.setRCode(code);
		         caller.runOnly();
		         code.showPlot(file);
		         
		      } catch (Exception e) {
		         // TODO: handle exception
		    	  logger.info("exception");
		      }
		
		  
	}
	
	
}






