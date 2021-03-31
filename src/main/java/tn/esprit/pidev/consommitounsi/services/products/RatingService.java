package tn.esprit.pidev.consommitounsi.services.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.consommitounsi.entities.products.Product;
import tn.esprit.pidev.consommitounsi.entities.products.ProductRating;
import tn.esprit.pidev.consommitounsi.repositories.products.ProductRatingRepository;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class RatingService extends HttpServlet {
    int i=0;


    @Autowired
    private ProductRatingRepository ratingRepository;


    public void service (HttpServletRequest request , HttpServletResponse response , ProductRating rating) throws IOException{
        //rating.setVisitCount();

    }

    public void incrementVisit(Product product){




    }

    //public void incrementVisit(List<Product> products){
        // TODO
    //}

    public void updatePersonalScore(List<Product> products) {




    }

    public void updateGlobalScore(List<Product> products) {
        // TODO
    }

    private double calculatePersonnalScore(List<Product> products) {
        // TODO



        return -1;
    }

   // private double calculateFinalScore(List<Product> products) {
     //   return (this.calculateGlobalScore(products) + this.calculatePersonnalScore(products)) / 2;
    //}



    // private double calculateGlobalScore(List<Product> products) {
        // TODO
        //rating
    // int average = 0, count = 0;

    //for(int i = 0; i < users.length; i++){
    //	if(rating[i][products] == -1)
    //			continue;
    //		average += rating[i][products];
    //	count++;
    //	}
    //	return ((double) average/count);

    //	//comments
    //   int averageComments=0, countComments=0
    //	for (int i = 0; i < users.length; i++){
    //	    if (comment[i][products]==-1)
    //	        continue;
    //	    averageComments+ =comment[i][products];
    //	    countComments++;
    //  }
    //	return ((double) averageComments/countComments);

		//visits
    //  int averageVisits=0,countVisits=0;
//
    //      for (int i=0;i< users.length; i++){
    //      if (countVisits[i][products]==-1)
    //          continue;
    //      averageVisits+=countVisits[i][products];
    //      countVisits++;

    //  }
    //  return((double) averageVisits/countVisits);


    //}
}
