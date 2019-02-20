/*
 * Mechanotherapy: how physical therapists\u2019 prescription of exercise promotes tissue repair
 Remove unicode while taking citation on Google sc
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.DriverManager;
import java.text.DateFormat;

import javax.swing.plaf.synth.SynthSeparatorUI;

import com.jaunt.JNode;
import com.jaunt.UserAgent;
import com.mysql.jdbc.Connection;

public class LTIExtract {
	static double altmetricid, mendeley_count, citeulike_count, connotea_count, blogs_count, news_count, twitter_count,
			wikipedia_count, qna_count, facebook_count, googleplus_count, policy_count, reddit_count,
			downloaduniqueips_count, downloadfulltext_count, downloadpdf_count, downloadabstract_count;
	static String downloadtimeline, abstractofpaper, authornames, journalname, subjectofpaper, titleofpaper,
			typeofpaper;
	static Date publish_date, lastdownloaddate;
	static double altmetric_score;
	static double altmetric_score_history_3month, altmetric_score_history_6month, altmetric_score_history_1year;
	static double poster_type_public, poster_type_researcher, poster_type_practitioner, poster_type_science_comm;
	static String twitter_cohorts, mendeley_by_status, twitter_geo_country_counts, mendeley_geo_country_counts;
	static Date blogpostedlastdate, twitterpostedlastdate, googlepluspostedlastdates, wikipediapostedlastdates,
			newspostedlastdates, redditpostedlastdates, policypostedlastdates, qnapostedlastdates,
			facebookpostedlastdates;

	public static void main(String[] args) throws FileNotFoundException, Exception {

		Class.forName("com.mysql.jdbc.Driver");
		Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/ltischema",
				"root", "toor");

		java.sql.PreparedStatement pstmt = con.prepareStatement(
				"insert into ltitable values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

		
		String mainfolder="C:\\Users\\syedm\\Desktop\\folder1";
		File maindir = new File(mainfolder);
		if(maindir.isDirectory()) {
			String[] children = maindir.list();
			for (int i = 0; i < children.length; i++) {
				//System.out.println("ewfwe"+children[i]);
				File subfolder=new File(mainfolder+"\\"+children[i]);
				if(subfolder.isDirectory()) {
					System.out.println("Folder:"+subfolder);
					
					
					

					File arr[] = subfolder.listFiles();
				
					for (int p = 0; p< arr.length; p++) {
						
						altmetricid=0; mendeley_count=0; citeulike_count=0; connotea_count=0; blogs_count=0; news_count=0; twitter_count=0;
						wikipedia_count=0; qna_count=0; facebook_count=0; googleplus_count=0; policy_count=0; reddit_count=0;
						downloaduniqueips_count=0; downloadfulltext_count=0; downloadpdf_count=0; downloadabstract_count=0;
				
						
						downloadtimeline=null; abstractofpaper=null; authornames=null; journalname=null; subjectofpaper=null; titleofpaper=null;typeofpaper=null;
						
						publish_date = null; lastdownloaddate= null;
						
						altmetric_score=0;
						
						altmetric_score_history_3month=0; altmetric_score_history_6month=0; altmetric_score_history_1year=0;
						
						poster_type_public=0; poster_type_researcher=0; poster_type_practitioner=0; poster_type_science_comm=0;
						
						twitter_cohorts=null; mendeley_by_status=null; twitter_geo_country_counts=null; mendeley_geo_country_counts=null;
						
						blogpostedlastdate=null; twitterpostedlastdate=null; googlepluspostedlastdates=null; wikipediapostedlastdates=null;
						newspostedlastdates=null; redditpostedlastdates=null; policypostedlastdates=null; qnapostedlastdates=null;
						facebookpostedlastdates=null;
						
						UserAgent userAgent = new UserAgent();
						userAgent.openJSON(new File(arr[p].toString())); // open JSON from a file

						try {
							JNode nodeid = userAgent.json.findFirst("altmetric_id");
							altmetricid = new Double(nodeid.toString());
						} catch (Exception e) {
							// TODO: handle exception
						}

						try {
							JNode nodemend = userAgent.json.findFirst("mendeley");
							mendeley_count = nodemend.toDouble();
						} catch (Exception e) {
							// TODO: handle exception
						}

						try {
							JNode nodecul = userAgent.json.findFirst("citeulike");
							citeulike_count = nodecul.toDouble();

						} catch (Exception e) {
							// TODO: handle exception
						}

						try {
							JNode nodeconn = userAgent.json.findFirst("connotea");
							connotea_count = nodeconn.toDouble();
						} catch (Exception e) {
							// TODO: handle exception
						}

						try {
							JNode nodeblog = userAgent.json.findFirst("blogs");
							blogs_count = nodeblog.findFirst("posts_count").toDouble();

						} catch (Exception e) {
							// TODO: handle exception
						}

						try {
							JNode nodenc = userAgent.json.findFirst("news");
							news_count = nodenc.findFirst("posts_count").toDouble();
						} catch (Exception e) {
							// TODO: handle exception
						}

						try {
							JNode nodetc = userAgent.json.findFirst("twitter");
							twitter_count = nodetc.findFirst("posts_count").toDouble();

						} catch (Exception e) {
							// e.printStackTrace();
						}

						try {
							JNode nodewp = userAgent.json.findFirst("wikipedia");
							wikipedia_count = nodewp.findFirst("posts_count").toDouble();
						} catch (Exception e) {
							// e.printStackTrace();
						}

						try {
							JNode nodefb = userAgent.json.findFirst("facebook");
							facebook_count = nodefb.findFirst("posts_count").toDouble();
						} catch (Exception e) {
							// e.printStackTrace();
						}

						try {
							JNode nodegp = userAgent.json.findFirst("googleplus");
							googleplus_count = nodegp.findFirst("posts_count").toDouble();
						} catch (Exception e) {
							// e.printStackTrace();
						}

						try {
							JNode nodeqna = userAgent.json.findFirst("q&a");
							qna_count = nodeqna.findFirst("posts_count").toDouble();
						} catch (Exception e) {
							// e.printStackTrace();
						}

						try {
							JNode nodepolc = userAgent.json.findFirst("policy");
							policy_count = nodepolc.findFirst("posts_count").toDouble();
						} catch (Exception e) {
							// e.printStackTrace();
						}

						try {
							JNode noderedc = userAgent.json.findFirst("reddit");
							reddit_count = noderedc.findFirst("posts_count").toDouble();
						} catch (Exception e) {
							// e.printStackTrace();
						}

						try {
							JNode nodeuniips = userAgent.json.findFirst("pmc");
							downloaduniqueips_count = nodeuniips.findFirst("unique_ips").toDouble();
						} catch (Exception e) {
							// e.printStackTrace();
						}

						try {
							JNode nodefulltxt = userAgent.json.findFirst("pmc");
							downloadfulltext_count = nodefulltxt.findFirst("full_text").toDouble();
						} catch (Exception e) {
							// e.printStackTrace();
						}

						try {
							JNode nodepdfcnt = userAgent.json.findFirst("pmc");
							downloadpdf_count = nodepdfcnt.findFirst("pdf").toDouble();
						} catch (Exception e) {
							// e.printStackTrace();
						}

						try {
							JNode nodeabscnt = userAgent.json.findFirst("pmc");
							downloadabstract_count = nodeabscnt.findFirst("abstract").toDouble();
						} catch (Exception e) {
							// e.printStackTrace();
						}

						try {
							JNode nodetimeline = userAgent.json.findFirst("pmc");
							downloadtimeline = nodetimeline.findFirst("timeline").toString();
							String datedwn = downloadtimeline.substring(2, downloadtimeline.length() - 2);
							// System.out.println(datedwn);
							String datesdwnn1[] = datedwn.split(",");
							// System.out.println(datesdwnn);

							String firstdate = datesdwnn1[0].split(":")[0];
							int lateyear = new Integer(firstdate.substring(3, 7));
							int latemonth = new Integer(firstdate.substring(8, 10));

							for (int cnt = 1; cnt < datesdwnn1.length; cnt++) {
								String da = datesdwnn1[cnt].split(":")[0];
								// System.out.println(da);
								int nextyear = new Integer(da.substring(4, 8));
								int nextmonth = new Integer(da.substring(9, 11));
								if (lateyear < nextyear) {
									lateyear = nextyear;
									latemonth = nextmonth;
								}
								if (lateyear == nextyear && latemonth < nextmonth) {
									latemonth = nextmonth;
								}
							}
							// System.out.println("latest date: "+lateyear+ " "+latemonth);

							lastdownloaddate = new Date(lateyear - 1900, latemonth - 1, 15);
							// System.out.println(lastdownloaddate);
						} catch (Exception e) {
							// e.printStackTrace();
						}

						try {
							JNode nodecitabs = userAgent.json.findFirst("citation");
							abstractofpaper = nodecitabs.findFirst("abstract").toString();
						} catch (Exception e) {
							// e.printStackTrace();
						}

						try {
							JNode nodearths = userAgent.json.findFirst("citation");
							authornames = nodearths.findFirst("authors").toString();
						} catch (Exception e) {
							// e.printStackTrace();
						}

						try {
							JNode nodejour = userAgent.json.findFirst("citation");
							journalname = nodejour.findFirst("journal").toString();
						} catch (Exception e) {
							// e.printStackTrace();
						}

						try {
							JNode nodesub = userAgent.json.findFirst("citation");
							subjectofpaper = nodesub.findFirst("subjects").toString();
						} catch (Exception e) {
							// e.printStackTrace();
						}

						try {
							JNode nodetitle = userAgent.json.findFirst("citation");
							titleofpaper = nodetitle.findFirst("title").toString();
						} catch (Exception e) {
							// e.printStackTrace();
						}

						try {
							JNode nodepapertype = userAgent.json.findFirst("citation");
							typeofpaper = nodepapertype.findFirst("type").toString();
						} catch (Exception e) {
							// e.printStackTrace();
						}

						try {
							JNode nodepubdate = userAgent.json.findFirst("citation");
							String pubdat = nodepubdate.findFirst("pubdate").toString();
							int pubyear = new Integer(pubdat.substring(0, 4));
							int pubmonth = new Integer(pubdat.substring(5, 7));
							int pubdate = new Integer(pubdat.substring(8, 10));
							publish_date = new Date(pubyear - 1900, pubmonth - 1, pubdate);
							// System.out.println(publish_date);
						} catch (Exception e) {
							// e.printStackTrace();
						}

						try {
							JNode nodealtscore = userAgent.json.findFirst("altmetric_score");
							JNode nodealtscore2 = nodealtscore.findFirst("score");
							altmetric_score = nodealtscore2.toDouble();

						} catch (Exception e) {
							// TODO: handle exception
						}

						try {
							JNode nodealtscorehis1y = userAgent.json.findFirst("score_history");
							altmetric_score_history_1year = nodealtscorehis1y.findFirst("1y").toDouble();
						} catch (Exception e) {
							// TODO: handle exception
						}

						try {
							JNode nodealtscorehis6m = userAgent.json.findFirst("score_history");
							altmetric_score_history_6month = nodealtscorehis6m.findFirst("6m").toDouble();
						} catch (Exception e) {
							// TODO: handle exception
						}

						try {
							JNode nodealtscorehis3m = userAgent.json.findFirst("score_history");
							altmetric_score_history_3month = nodealtscorehis3m.findFirst("3m").toDouble();
						} catch (Exception e) {
							// TODO: handle exception
						}

						try {
							JNode nodeptm = userAgent.json.findFirst("poster_types");
							poster_type_public = nodeptm.findFirst("member_of_the_public").toDouble();
						} catch (Exception e) {
							// TODO: handle exception
						}

						try {
							JNode nodeptr = userAgent.json.findFirst("poster_types");
							poster_type_researcher = nodeptr.findFirst("researcher").toDouble();
						} catch (Exception e) {
							// TODO: handle exception
						}

						try {
							JNode nodeptp = userAgent.json.findFirst("poster_types");
							poster_type_practitioner = nodeptp.findFirst("practitioner").toDouble();
						} catch (Exception e) {
							// TODO: handle exception
						}

						try {
							JNode nodeptsc = userAgent.json.findFirst("poster_types");
							poster_type_science_comm = nodeptsc.findFirst("science_communicator").toDouble();
						} catch (Exception e) {
							// TODO: handle exception
						}

						try {
							JNode dem = userAgent.json.findFirst("demographics");
							JNode nodetwicoh = dem.findFirst("twitter");
							twitter_cohorts = nodetwicoh.findFirst("cohorts").toString();
						} catch (Exception e) {
							// TODO: handle exception
						}

						try {
							JNode dem = userAgent.json.findFirst("demographics");
							JNode nodemendstatus = dem.findFirst("mendeley");
							mendeley_by_status = nodemendstatus.findFirst("by_status").toString();
						} catch (Exception e) {
							// TODO: handle exception
						}

						try {
							JNode nodetwigeo = userAgent.json.findFirst("geo");
							twitter_geo_country_counts = nodetwigeo.findFirst("twitter").toString();
						} catch (Exception e) {
							// TODO: handle exception
						}

						try {
							JNode nodemengeo = userAgent.json.findFirst("geo");
							mendeley_geo_country_counts = nodemengeo.findFirst("mendeley").toString();
						} catch (Exception e) {
							// TODO: handle exception
						}

						try {
							JNode nodeposted = userAgent.json.findFirst("posts");
							JNode nodeposteddate = nodeposted.findFirst("blogs");
							String hhh = nodeposteddate.findEvery("posted_on").toString();
							hhh = hhh.substring(1, hhh.length());
							String[] alldates = hhh.split(",");
							int lastyear = new Integer(alldates[0].substring(1, 5));
							int lastmonth = new Integer(alldates[0].substring(6, 8));
							int lastdate = new Integer(alldates[0].substring(9, 11));
							// System.out.println("hhwewevlnwevln");
							// for (int j = 0; j < alldates.length; j++) {
							// System.out.println(alldates[j]);
							// }
							for (int j = 1; j < alldates.length; j++) {
								int yr = new Integer(alldates[j].substring(1, 5));
								int mon = new Integer(alldates[j].substring(6, 8));
								int dat = new Integer(alldates[j].substring(9, 11));
								if (lastyear < yr) {
									lastyear = yr;
									lastmonth = mon;
									lastdate = dat;
								}
								if (lastyear == yr && lastmonth < mon) {
									lastmonth = mon;
									lastdate = dat;
								}
								if (lastyear == yr && lastmonth == mon && lastdate < dat) {
									lastdate = dat;
								}
							}
							// System.out.println(lastyear);
							// System.out.println(lastmonth);
							// System.out.println(lastdate);
							blogpostedlastdate = new Date(lastyear - 1900, lastmonth - 1, lastdate);
							// System.out.println(blogpostedlastdate);
						} catch (Exception e) {
							// e.printStackTrace();
						}

						try {
							JNode nodeposted = userAgent.json.findFirst("posts");
							JNode nodeposteddate = nodeposted.findFirst("twitter");
							String hhh = nodeposteddate.findEvery("posted_on").toString();
							hhh = hhh.substring(1, hhh.length());
							String[] alldates = hhh.split(",");
							int lastyear = new Integer(alldates[0].substring(1, 5));
							int lastmonth = new Integer(alldates[0].substring(6, 8));
							int lastdate = new Integer(alldates[0].substring(9, 11));
							// System.out.println("hhwewevlnwevln");
							// for (int j = 0; j < alldates.length; j++) {
							// System.out.println(alldates[j]);
							// }
							for (int j = 1; j < alldates.length; j++) {
								int yr = new Integer(alldates[j].substring(1, 5));
								int mon = new Integer(alldates[j].substring(6, 8));
								int dat = new Integer(alldates[j].substring(9, 11));
								if (lastyear < yr) {
									lastyear = yr;
									lastmonth = mon;
									lastdate = dat;
								}
								if (lastyear == yr && lastmonth < mon) {
									lastmonth = mon;
									lastdate = dat;
								}
								if (lastyear == yr && lastmonth == mon && lastdate < dat) {
									lastdate = dat;
								}
							}
							// System.out.println(lastyear);
							// System.out.println(lastmonth);
							// System.out.println(lastdate);
							twitterpostedlastdate = new Date(lastyear - 1900, lastmonth - 1, lastdate);
							// System.out.println(blogpostedlastdate);
						} catch (Exception e) {
							// e.printStackTrace();
						}

						try {
							JNode nodeposted = userAgent.json.findFirst("posts");
							JNode nodeposteddate = nodeposted.findFirst("googleplus");
							String hhh = nodeposteddate.findEvery("posted_on").toString();
							hhh = hhh.substring(1, hhh.length());
							String[] alldates = hhh.split(",");
							int lastyear = new Integer(alldates[0].substring(1, 5));
							int lastmonth = new Integer(alldates[0].substring(6, 8));
							int lastdate = new Integer(alldates[0].substring(9, 11));
							// System.out.println("hhwewevlnwevln");
							// for (int j = 0; j < alldates.length; j++) {
							// System.out.println(alldates[j]);
							// }
							for (int j = 1; j < alldates.length; j++) {
								int yr = new Integer(alldates[j].substring(1, 5));
								int mon = new Integer(alldates[j].substring(6, 8));
								int dat = new Integer(alldates[j].substring(9, 11));
								if (lastyear < yr) {
									lastyear = yr;
									lastmonth = mon;
									lastdate = dat;
								}
								if (lastyear == yr && lastmonth < mon) {
									lastmonth = mon;
									lastdate = dat;
								}
								if (lastyear == yr && lastmonth == mon && lastdate < dat) {
									lastdate = dat;
								}
							}
							// System.out.println(lastyear);
							// System.out.println(lastmonth);
							// System.out.println(lastdate);
							googlepluspostedlastdates = new Date(lastyear - 1900, lastmonth - 1, lastdate);
							// System.out.println(blogpostedlastdate);
						} catch (Exception e) {
							// e.printStackTrace();
						}

						try {
							JNode nodeposted = userAgent.json.findFirst("posts");
							JNode nodeposteddate = nodeposted.findFirst("wikipedia");
							String hhh = nodeposteddate.findEvery("posted_on").toString();
							hhh = hhh.substring(1, hhh.length());
							String[] alldates = hhh.split(",");
							int lastyear = new Integer(alldates[0].substring(1, 5));
							int lastmonth = new Integer(alldates[0].substring(6, 8));
							int lastdate = new Integer(alldates[0].substring(9, 11));
							// System.out.println("hhwewevlnwevln");
							// for (int j = 0; j < alldates.length; j++) {
							// System.out.println(alldates[j]);
							// }
							for (int j = 1; j < alldates.length; j++) {
								int yr = new Integer(alldates[j].substring(1, 5));
								int mon = new Integer(alldates[j].substring(6, 8));
								int dat = new Integer(alldates[j].substring(9, 11));
								if (lastyear < yr) {
									lastyear = yr;
									lastmonth = mon;
									lastdate = dat;
								}
								if (lastyear == yr && lastmonth < mon) {
									lastmonth = mon;
									lastdate = dat;
								}
								if (lastyear == yr && lastmonth == mon && lastdate < dat) {
									lastdate = dat;
								}
							}
							// System.out.println(lastyear);
							// System.out.println(lastmonth);
							// System.out.println(lastdate);
							wikipediapostedlastdates = new Date(lastyear - 1900, lastmonth - 1, lastdate);
							// System.out.println(blogpostedlastdate);
						} catch (Exception e) {
							// e.printStackTrace();
						}

						try {
							JNode nodeposted = userAgent.json.findFirst("posts");
							JNode nodeposteddate = nodeposted.findFirst("news");
							String hhh = nodeposteddate.findEvery("posted_on").toString();
							hhh = hhh.substring(1, hhh.length());
							String[] alldates = hhh.split(",");
							int lastyear = new Integer(alldates[0].substring(1, 5));
							int lastmonth = new Integer(alldates[0].substring(6, 8));
							int lastdate = new Integer(alldates[0].substring(9, 11));
							// System.out.println("hhwewevlnwevln");
							// for (int j = 0; j < alldates.length; j++) {
							// System.out.println(alldates[j]);
							// }
							for (int j = 1; j < alldates.length; j++) {
								int yr = new Integer(alldates[j].substring(1, 5));
								int mon = new Integer(alldates[j].substring(6, 8));
								int dat = new Integer(alldates[j].substring(9, 11));
								if (lastyear < yr) {
									lastyear = yr;
									lastmonth = mon;
									lastdate = dat;
								}
								if (lastyear == yr && lastmonth < mon) {
									lastmonth = mon;
									lastdate = dat;
								}
								if (lastyear == yr && lastmonth == mon && lastdate < dat) {
									lastdate = dat;
								}
							}
							// System.out.println(lastyear);
							// System.out.println(lastmonth);
							// System.out.println(lastdate);
							newspostedlastdates = new Date(lastyear - 1900, lastmonth - 1, lastdate);
							// System.out.println(blogpostedlastdate);
						} catch (Exception e) {
							// e.printStackTrace();
						}

						try {
							JNode nodeposted = userAgent.json.findFirst("posts");
							JNode nodeposteddate = nodeposted.findFirst("reddit");
							String hhh = nodeposteddate.findEvery("posted_on").toString();
							hhh = hhh.substring(1, hhh.length());
							String[] alldates = hhh.split(",");
							int lastyear = new Integer(alldates[0].substring(1, 5));
							int lastmonth = new Integer(alldates[0].substring(6, 8));
							int lastdate = new Integer(alldates[0].substring(9, 11));
							// System.out.println("hhwewevlnwevln");
							// for (int j = 0; j < alldates.length; j++) {
							// System.out.println(alldates[j]);
							// }
							for (int j = 1; j < alldates.length; j++) {
								int yr = new Integer(alldates[j].substring(1, 5));
								int mon = new Integer(alldates[j].substring(6, 8));
								int dat = new Integer(alldates[j].substring(9, 11));
								if (lastyear < yr) {
									lastyear = yr;
									lastmonth = mon;
									lastdate = dat;
								}
								if (lastyear == yr && lastmonth < mon) {
									lastmonth = mon;
									lastdate = dat;
								}
								if (lastyear == yr && lastmonth == mon && lastdate < dat) {
									lastdate = dat;
								}
							}
							// System.out.println(lastyear);
							// System.out.println(lastmonth);
							// System.out.println(lastdate);
							redditpostedlastdates = new Date(lastyear - 1900, lastmonth - 1, lastdate);
							// System.out.println(blogpostedlastdate);
						} catch (Exception e) {
							// e.printStackTrace();
						}

						try {
							JNode nodeposted = userAgent.json.findFirst("posts");
							JNode nodeposteddate = nodeposted.findFirst("policy");
							String hhh = nodeposteddate.findEvery("posted_on").toString();
							hhh = hhh.substring(1, hhh.length());
							String[] alldates = hhh.split(",");
							int lastyear = new Integer(alldates[0].substring(1, 5));
							int lastmonth = new Integer(alldates[0].substring(6, 8));
							int lastdate = new Integer(alldates[0].substring(9, 11));
							// System.out.println("hhwewevlnwevln");
							// for (int j = 0; j < alldates.length; j++) {
							// System.out.println(alldates[j]);
							// }
							for (int j = 1; j < alldates.length; j++) {
								int yr = new Integer(alldates[j].substring(1, 5));
								int mon = new Integer(alldates[j].substring(6, 8));
								int dat = new Integer(alldates[j].substring(9, 11));
								if (lastyear < yr) {
									lastyear = yr;
									lastmonth = mon;
									lastdate = dat;
								}
								if (lastyear == yr && lastmonth < mon) {
									lastmonth = mon;
									lastdate = dat;
								}
								if (lastyear == yr && lastmonth == mon && lastdate < dat) {
									lastdate = dat;
								}
							}
							// System.out.println(lastyear);
							// System.out.println(lastmonth);
							// System.out.println(lastdate);
							policypostedlastdates = new Date(lastyear - 1900, lastmonth - 1, lastdate);
							// System.out.println(blogpostedlastdate);
						} catch (Exception e) {
							// e.printStackTrace();
						}

						try {
							JNode nodeposted = userAgent.json.findFirst("posts");
							JNode nodeposteddate = nodeposted.findFirst("q&a");
							String hhh = nodeposteddate.findEvery("posted_on").toString();
							hhh = hhh.substring(1, hhh.length());
							String[] alldates = hhh.split(",");
							int lastyear = new Integer(alldates[0].substring(1, 5));
							int lastmonth = new Integer(alldates[0].substring(6, 8));
							int lastdate = new Integer(alldates[0].substring(9, 11));
							// System.out.println("hhwewevlnwevln");
							// for (int j = 0; j < alldates.length; j++) {
							// System.out.println(alldates[j]);
							// }
							for (int j = 1; j < alldates.length; j++) {
								int yr = new Integer(alldates[j].substring(1, 5));
								int mon = new Integer(alldates[j].substring(6, 8));
								int dat = new Integer(alldates[j].substring(9, 11));
								if (lastyear < yr) {
									lastyear = yr;
									lastmonth = mon;
									lastdate = dat;
								}
								if (lastyear == yr && lastmonth < mon) {
									lastmonth = mon;
									lastdate = dat;
								}
								if (lastyear == yr && lastmonth == mon && lastdate < dat) {
									lastdate = dat;
								}
							}
							// System.out.println(lastyear);
							// System.out.println(lastmonth);
							// System.out.println(lastdate);
							qnapostedlastdates = new Date(lastyear - 1900, lastmonth - 1, lastdate);
							// System.out.println(blogpostedlastdate);
						} catch (Exception e) {
							// e.printStackTrace();
						}

						try {
							JNode nodeposted = userAgent.json.findFirst("posts");
							JNode nodeposteddate = nodeposted.findFirst("facebook");
							String hhh = nodeposteddate.findEvery("posted_on").toString();
							hhh = hhh.substring(1, hhh.length());
							String[] alldates = hhh.split(",");
							int lastyear = new Integer(alldates[0].substring(1, 5));
							int lastmonth = new Integer(alldates[0].substring(6, 8));
							int lastdate = new Integer(alldates[0].substring(9, 11));
							// System.out.println("hhwewevlnwevln");
							// for (int j = 0; j < alldates.length; j++) {
							// System.out.println(alldates[j]);
							// }
							for (int j = 1; j < alldates.length; j++) {
								int yr = new Integer(alldates[j].substring(1, 5));
								int mon = new Integer(alldates[j].substring(6, 8));
								int dat = new Integer(alldates[j].substring(9, 11));
								if (lastyear < yr) {
									lastyear = yr;
									lastmonth = mon;
									lastdate = dat;
								}
								if (lastyear == yr && lastmonth < mon) {
									lastmonth = mon;
									lastdate = dat;
								}
								if (lastyear == yr && lastmonth == mon && lastdate < dat) {
									lastdate = dat;
								}
							}
							// System.out.println(lastyear);
							// System.out.println(lastmonth);
							// System.out.println(lastdate);
							facebookpostedlastdates = new Date(lastyear - 1900, lastmonth - 1, lastdate);
							// System.out.println(blogpostedlastdate);
						} catch (Exception e) {
							// e.printStackTrace();
						}

			/*			
						System.out.println("Altmetric Id " + altmetricid);
						System.out.println("Mendeley Count " + mendeley_count);
						System.out.println("cc count" + citeulike_count);
						System.out.println("con cmt" + connotea_count);
						System.out.println("bc" + blogs_count);
						System.out.println("nc " + news_count);
						System.out.println("tc" + twitter_count);
						System.out.println("wc" + wikipedia_count);
						System.out.println("fbc" + facebook_count);
						System.out.println("gpc" + googleplus_count);
						System.out.println("qnac" + qna_count);
						System.out.println("ploc" + policy_count);
						System.out.println("redc" + reddit_count);
						System.out.println("dwnip" + downloaduniqueips_count);
						System.out.println("dwnt" + downloadfulltext_count);
						System.out.println("dwn pdf" + downloadpdf_count);
						System.out.println("dwn abs" + downloadabstract_count);
						System.out.println("dwn timeli" + downloadtimeline);
						System.out.println("last dwv" + lastdownloaddate);
						System.out.println("abs" + abstractofpaper);
						System.out.println("auth" + authornames);
						System.out.println("jour" + journalname);
						System.out.println("subj" + subjectofpaper);
						System.out.println("title" + titleofpaper);
						System.out.println("type" + typeofpaper);
						System.out.println("pubdat" + publish_date);
						System.out.println("alt scr" + altmetric_score);
						System.out.println("alt scr gs" + altmetric_score_history_1year);
						System.out.println("alt scr gs" + altmetric_score_history_6month);
						System.out.println("alt scr gs" + altmetric_score_history_3month);
						System.out.println("pos typ prac" + poster_type_practitioner);
						System.out.println("pos typ pub" + poster_type_public);
						System.out.println("pos typ res" + poster_type_researcher);
						System.out.println("pos typ sci" + poster_type_science_comm);
						System.out.println("tewi coh" + twitter_cohorts);
						System.out.println("men sta" + mendeley_by_status);
						System.out.println("twi geo" + twitter_geo_country_counts);
						System.out.println("men geo" + mendeley_geo_country_counts);
						System.out.println("blog last" + blogpostedlastdate);
						System.out.println("tw last" + twitterpostedlastdate);
						System.out.println("gp last" + googlepluspostedlastdates);
						System.out.println("wiki last" + wikipediapostedlastdates);
						System.out.println("nws last" + newspostedlastdates);
						System.out.println("red last" + redditpostedlastdates);
						System.out.println("pol last" + policypostedlastdates);
						System.out.println("qna last" + qnapostedlastdates);
						System.out.println("face last" + facebookpostedlastdates);
						
				*/

						/*
						 * pstmt.setDouble(1,altmetricid); pstmt.setDouble(2, mendeley_count);
						 * pstmt.setDouble(3, citeulike_count); pstmt.setDouble(4, connotea_count);
						 * pstmt.setDouble(5, blogs_count); pstmt.setDouble(6, news_count);
						 * pstmt.setDouble(7, twitter_count); pstmt.setDouble(8, wikipedia_count);
						 * pstmt.setDouble(9, altmetric_score);
						 * 
						 * pstmt.execute();
						 * 
						 * System.out.println("File Number :"+arr[i]+ " done");
						 */
						
						pstmt.setDouble(1,altmetricid);
					      pstmt.setDouble(2, mendeley_count);
					      pstmt.setDouble(3, citeulike_count);
					      pstmt.setDouble(4, connotea_count);
					      pstmt.setDouble(5, blogs_count);
					      pstmt.setDouble(6, news_count);
					      pstmt.setDouble(7, twitter_count);
					      pstmt.setDouble(8, wikipedia_count);
					      pstmt.setDouble(9, facebook_count);
					      pstmt.setDouble(10,googleplus_count);
					      pstmt.setDouble(11,qna_count);
					      pstmt.setDouble(12,policy_count);
					      pstmt.setDouble(13,reddit_count);
					      pstmt.setDouble(14,downloaduniqueips_count);
					      pstmt.setDouble(15,downloadfulltext_count);
					      pstmt.setDouble(16,downloadpdf_count);
					      pstmt.setDouble(17,downloadabstract_count);
					      pstmt.setString(18,downloadtimeline);
					      pstmt.setDate(19,lastdownloaddate);
					      pstmt.setString(20,abstractofpaper);
					      pstmt.setString(21,authornames);
					      pstmt.setString(22,journalname);
					      pstmt.setString(23,subjectofpaper);
					      pstmt.setString(24,titleofpaper);
					      pstmt.setString(25,typeofpaper);
					      pstmt.setDate(26,publish_date);
					      pstmt.setDouble(27,altmetric_score);
					      pstmt.setDouble(28,altmetric_score_history_1year);
					      pstmt.setDouble(29,altmetric_score_history_6month);
					      pstmt.setDouble(30,altmetric_score_history_3month);
					      pstmt.setDouble(31,poster_type_practitioner);
					      pstmt.setDouble(32,poster_type_public);
					      pstmt.setDouble(33,poster_type_researcher);
					      pstmt.setDouble(34,poster_type_science_comm);
					      pstmt.setString(35,twitter_cohorts);
					      pstmt.setString(36,mendeley_by_status);
					      pstmt.setString(37,twitter_geo_country_counts);
					      pstmt.setString(38,mendeley_geo_country_counts);
					      pstmt.setDate(39,blogpostedlastdate);
					      pstmt.setDate(40,twitterpostedlastdate);
					      pstmt.setDate(41,googlepluspostedlastdates);
					      pstmt.setDate(42,wikipediapostedlastdates);
					      pstmt.setDate(43,newspostedlastdates);
					      pstmt.setDate(44,redditpostedlastdates);
					      pstmt.setDate(45,policypostedlastdates);
					      pstmt.setDate(46,qnapostedlastdates);
					      pstmt.setDate(47,facebookpostedlastdates);
					      
					      pstmt.execute();
					      
					      System.out.println("File Number :"+arr[p]+ " done");


					
					
					
					
				}
				
			}
			//System.out.println("Total"+children.length);
					

		}

	}
}
}
