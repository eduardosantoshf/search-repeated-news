package modules;

public class News {
	private String newsTitle;
	private String newsContent;
	
	/*********************************************** constructor ***********************************************/

	public News(String newsTitle, String newsContent) {
		this.newsTitle = newsTitle;
		this.newsContent = newsContent;
	}
	
	/******************************************* getters and setters *******************************************/
	
	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	public String getNewsContent() {
		return newsContent;
	}
	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}
	
	/************************************************* toString *************************************************/
	
	@Override
	public String toString() {
		return "News [newsTitle=" + newsTitle + ", newsContent=" + newsContent + "]";
	}
	
	
}
