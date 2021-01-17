<%@page import="fr.eni.encheres.bo.Enchere"%>
<%@page import="java.util.Locale"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@page import="fr.eni.encheres.bo.ArticleVendu"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
    <html lang="fr">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!--StyleSheet-->
        <link rel="stylesheet" href="styles/style.css">
   		<link rel="stylesheet" href="styles/navbar.css">
   		<link rel="stylesheet" href="styles/remporteVente.css">
        <!--Font Awesome CDN-->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />
        <!--Script-->
        <script src="script.js" defer></script>
        <title>Eni-Enchères | Connexion</title>
        
    </head>
  <body>
  <!-- Navbar -->
 	<%@ include file = "navbar.jsp" %>
   <!--Head-->
    <div class="head">
      <h1>Détail Vente</h1>
    </div>
    <% Utilisateur connectedUser = (Utilisateur) session.getAttribute("ConnectedUser"); %>
	<% ArticleVendu article = (ArticleVendu)request.getAttribute("ArticleAffiche"); %>
    <div class="container-vente">
        <div class="card-img-container">
            <img src="img/tournevis.jpeg" alt="">
        </div>
        <table>
       		 <tr>
                <td class="td1"><p class="value-td1">Nom:</p></td>
                <td class="td2"><p class="value-td2"><%=article.getNom() %></p></td>
            </tr>
            <tr>
                <td class="td1"><p class="value-td1">Description:</p></td>
                <td class="td2"><p class="value-td2"><%=article.getDescription() %></p></td>
            </tr>
            <%if(article.getPrixVente() != 0) { %>
            <tr>
                <td class="td1"><p class="value-td1">Meilleure offre:</p></td>
                <td class="td2"><p class="value-td2"><%=article.getPrixVente()%> pts </p></td>
            </tr>
            <% } %>
            <tr>
                <td class="td1"><p class="value-td1">Mise à prix:</p></td>
                <td class="td2"><p class="value-td2"><%=article.getMiseAPrix()  %> pts</p></td>
            </tr>  
            <tr>
                <td class="td1"><p class="value-td1">Fin de l'enchère:</p></td>
                <td class="td2"><p class="value-td2"><%=article.getDateFinEncheres().format(DateTimeFormatter.ofPattern("dd/MM/YYYY", Locale.FRANCE)) %></p></td>
            </tr>
            <tr>
                <td class="td1"><p class="value-td1">Retrait:</p></td>
                <td class="td2"><p class="value-td2"><%=article.getLieuRetrait().getRue()  %>, <%=article.getLieuRetrait().getCodePostal()%>, <%=article.getLieuRetrait().getVille() %></p></td>
            </tr>
            <tr>
                <td class="td1"><p class="value-td1">Vendeur:</p></td>
                <td class="td2"><p class="value-td2"><%=article.getVendeur().getPseudo() %></p></td>
            </tr> 
        </table>
	<% if(!article.getVendeur().getPseudo().equals(connectedUser.getPseudo())) { %>
		<form action="<%=request.getContextPath()%>/encherir" method="post">
			<div class="input-field">
				<label for="mPrix">Ma Proposition :</label>
				<input class="input" type="number" name="mPrix" id="mPrix" step="1" max= "10000" required>
			</div>
		    <div>
		        <button class="btn-login" type="submit">Enchérir</button>
		    </div>
	      <input value="<%=article.getId()%>" type="text" id="idArticle" name="idArticle" style="visibility:hidden;">
		</form>
	<% } else { %>
		<a href ="<%=request.getContextPath()%>/updateVente?idArticle=<%=article.getId()%>"><button class="btn" type="button"> 
        Modifier
     	 </button>
     	 </a>
     	 <a href ="<%=request.getContextPath()%>/accueilConnected"><button class="btn" type="button">
        Annuler
     	 </button>
     	 </a>
	<% } %>
      <a href ="<%=request.getContextPath()%>/accueilConnected"><button class="btn" type="button"> <!--change that with index.html file location-->
        Back
      </button>
      </a>
    </div>
  </body>
</html>
