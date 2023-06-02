package feed;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.Serializable;

import namedEntity.*;
import namedEntity.heuristic.Heuristic;

/*Esta clase modela el contenido de un articulo (ie, un item en el caso del rss feed) */

public class Article implements Serializable {
	private String title;
	private String text;
	private Date publicationDate;
	private String link;

	private static List<EntidadNombrada> namedEntityList = new ArrayList<EntidadNombrada>();


	public Article(String title, String text, Date publicationDate, String link) {
		super();
		this.title = title;
		this.text = text;
		this.publicationDate = publicationDate;
		this.link = link;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "Article [title=" + title + ", text=" + text + ", publicationDate=" + publicationDate + ", link=" + link
				+ "]";
	}



	public EntidadNombrada getNamedEntity(String namedEntity){
		for (EntidadNombrada n: namedEntityList){
			if (n.getName().compareTo(namedEntity) == 0){
				return n;
			}
		}
		return null;
	}

    public List<String> computeNamedEntities(Heuristic h) {
        String text = this.getTitle() + " " + this.getText();

        String[] words = text.replaceAll("[.,;:()'!?&=\\n]", "").split(" ");
        List<String> namedEntitiesStrings = new ArrayList<>();

        for (String word : words) {
            if (h.isEntity(word)) {
                namedEntitiesStrings.add(word);
            }
        }

        return namedEntitiesStrings;
    }

	public void prettyPrint() {
		System.out.println("Title: " + this.getTitle());
		System.out.println("Publication Date: " + this.getPublicationDate());
		System.out.println("Link: " + this.getLink());
		System.out.println("Text: " + (this.getText().length() > 256 ? this.getText().substring(0, 255) + "..." : this.getText()).replace("\n", " "));
		System.out.println("**********************************************************************************************");

	}

	public static void main(String[] args) {
		  Article a = new Article("This Historically Black University Created Its Own Tech Intern Pipeline",
			  "A new program at Bowie State connects computing students directly with companies, bypassing an often harsh Silicon Valley vetting process",
			  new Date(),
			  "https://www.nytimes.com/2023/04/05/technology/bowie-hbcu-tech-intern-pipeline.html"
			  );

		  a.prettyPrint();
	}


}



