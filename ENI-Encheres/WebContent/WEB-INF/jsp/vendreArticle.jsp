<%@page import="fr.eni.encheres.bo.Categorie"%>
<%@page import="java.util.List"%>
<%@page import="fr.eni.encheres.bll.CategorieManager"%>
<%@page import="fr.eni.encheres.bo.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
  	    <meta name="viewport"content="width=device-width, initial-scale=1.">
  	    <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <!-- Place your kit's code here -->
        <script src="https://kit.fontawesome.com/1cbc331981.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="styles/style.css">
   	 	<link rel="stylesheet" href="styles/navbar.css">
   	 	<link rel="stylesheet" href="styles/vendreArticle.css">
        <title>ENI-Vendre</title>
    </head>
<body>
	<!-- Navbar -->
 	<%@ include file = "navbar.jsp" %>
 	<!--Head-->
    <div class="head">
      <h1>Vendre un article</h1>
    </div>

 <!-- Formulaire Vendre un article-->
 
    <section class="vente-form">
    	<% Utilisateur connectedUser = (Utilisateur) session.getAttribute("ConnectedUser"); 
    	  /* List<Categorie> allCategories = CategorieManager.selectionnerToutesLesCategories();*/
    	%>    
      <form class="vente" action="<%=request.getContextPath() %>/vendre" method="post"> <!-- change methode to POST to get results on server, i used GET to show the result in html file-->
      
        <div class="input-field">
          <label for="article">Article :</label>
          <input class="input" type="text" name="article" id="article"  placeholder="Article" required>
        </div>

        <div class="input-field">
          <label for="description">Description :</label>
          <textarea class="input"name="description" id="description">Entrez une description de l'article</textarea>
        </div>

        <div class="input-field"> <!--Un div peut être?-->
          <label for="categorie">Catégorie :</label>
          <select class="input" id="categorie">
          <% for(Categorie categorie : CategorieManager.selectionnerToutesLesCategories()) { %>
            <option name="categorie" value ="<%=categorie.getId()%>"><%=categorie.getLibelle()%></option>
            <% } %>
          </select>
        </div>
      
        <div class="input-field">
          <label for="file">Photo de l'article :</label>
          <div class="input"> 
            <input type="file" name="file" id="file" accept="image/*"> <!--Only helps the user, need to put protection in the backend-->
            <input type="submit">
          </div>
        </div>
       
        <div class="input-field">
              <label for="mPrix">Mise à prix :</label>
              <input class="input" type="number" name="mPrix" id="mPrix" 
              step="5" max= "10000" required>
        </div>

        <div class="input-field">
            <label for="dEnchere">Début de l'enchère :</label>
            <input class="input" type="date" name="dEnchere" id="dEnchere" required> 
        </div>

        <div class="input-field">
            <label for="fEnchere">Fin de l'enchère :</label>
            <input class= "input" type="date" name="fEnchere" id="fEnchere" required> 
        </div>

    <!-- Retrait-->
    <div class="retrait">
      <h1>Retrait de l'objet</h1>
      <div class="input-field">
          <label for="rue">Rue :</label>
          <input class="input" type="text" name="rue" id="rue" maxlength="100" value="<%=connectedUser.getRue() %>" required>
      </div>

      <div class="input-field">
          <label for="cp">Code Postale :</label>
          <input class="input" type="text" name="codePostal" id="cp" 
          step="1000" min="0" maxlength="5" value="<%=connectedUser.getCodePostal() %>" required>
      </div>

      <div class="input-field">
          <label for="ville">Ville :</label>
          <input class="input" type="text" name="ville" id="ville" value="<%=connectedUser.getVille() %>" required>
      </div>
    </div>

        <div>
          <button class="btn-login" type="submit" >Vendre cet article</button>
        </div>

       
      </form>
    </section>

</body>
</html>