import java.util.ArrayList;

import org.json.simple.JSONObject;

public class ArticleDao {

	public ArrayList<Article> articles;
	// static 함부로 쓰지 말기 <-> 객체지향적 프로그램과 반대
	private int no = 4;

	public ArticleDao() {
		
		articles = new ArrayList<Article>();
		Article list1 = new Article(1, "제목1", "내용1", 1, Util.getCurrenDate() , 10, 1);
		Article list2 = new Article(2, "제목2", "내용2", 2, Util.getCurrenDate(), 13, 5);
		Article list3 = new Article(3, "제목3", "내용3", 3, Util.getCurrenDate(), 25, 7);

		articles.add(list1);
		articles.add(list2);
		articles.add(list3);
		
		for (int i = no; i <= 50; i++) {
			Article a4 = new Article();
			a4.setRegId(i);
			a4.setTitle("제목" + i);
			a4.setBody("내용" + i);
			a4.getMid();
			Util.getCurrenDate();

			articles.add(a4);
		}


	}

	// 등록번호
	public void insertArticle(Article a) {
		a.setRegId(no);
		no++;

		a.setDate(Util.getCurrenDate());

		// articles.add(a); -> 이거 아니고 파일저장
	}
	
	public void ArticleToJsonFile() {
		JSONObject obj = new JSONObject();
		String jsonText;

		obj.put("id", new Integer(1));
		
		jsonText = out.toString();
	}

	// 제거
	public void removeArticle(Article a) {
		articles.remove(a);
	}
	
	//
	public Article getArticleByRegId(int targetId) {
		for (int i = 0; i < articles.size(); i++) {
			int id = articles.get(i).getRegId();
			if (id == targetId) {
				return articles.get(i);
			}
		}
		return null;
	}

	// 제목과 내용으로 검색
	public ArrayList<Article> getSearchedArticleByflag(int flag, String Keyword) {

		ArrayList<Article> SearchedArticle = new ArrayList<>();

		for (int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);

			String str = article.getPropertiesByFlag(flag); // Properties : 속성
			if (str.contains(Keyword)) {
				SearchedArticle.add(article);
			}
		}

		return SearchedArticle;
	}

	// 내용으로 검색
	public ArrayList<Article> getSearchedArticleByBody(String Keyword) {
		ArrayList<Article> SearchedArticle = new ArrayList<>();

		for (int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);

			String str = article.getBody(); // 각 게시물 내용
			if (str.contains(Keyword)) {
				SearchedArticle.add(article);
			}
		}

		return SearchedArticle;

	}
	
	public ArrayList<Article> getArticles() {
		return articles;
	}

}
