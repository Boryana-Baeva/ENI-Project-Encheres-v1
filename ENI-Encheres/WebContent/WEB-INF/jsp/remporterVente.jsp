<%@page import="fr.eni.encheres.bo.Enchere"%>
<%@page import="fr.eni.encheres.bo.ArticleVendu"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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

    <!--Navbar-->
    <%@ include file = "navbar.jsp" %>
    
    
    <!--Head-->
    <div class="head">
      <h1>Vente Remportée</h1>
    </div>

    <div class="container-vente">
      <div class="encherisseur">
        <h1>Vous avez remporté la vente</h1>
      </div>
        
        <div class="top-container-vente">
          <div class="card-img-container">
              <img src="img/tournevis.jpeg" alt="">
          </div>
        </div>
        <%ArticleVendu article = (ArticleVendu)request.getAttribute("articleRemporte"); %>
        <%Enchere enchere = (Enchere)request.getAttribute("meilleureEnchere"); %>
        <table>
            <tr>
              <td class="td1"><p class="value-td1">Nom de l'article:</p></td>
              <td class="td2"><p class="value-td2"><%=article.getNom() %></p></td>
            </tr>
            <tr>
                <td class="td1"><p class="value-td1">Description:</p></td>
                <td class="td2"><p class="value-td2"><%=article.getDescription() %></p></td>
            </tr>
            <%if(article.getPrixVente() != 0) { %>
            <tr>
                <td class="td1"><p class="value-td1">Meilleure offre:</p></td>
                <td class="td2"><p class="value-td2"><%=article.getPrixVente()%> pts par <%=enchere.getEncherisseur().getPseudo() %>  </p></td>
            </tr>
             <% } %>
            <tr>
                <td class="td1"><p class="value-td1">Mise à prix:</p></td>
                <td class="td2"><p class="value-td2"><%=article.getMiseAPrix()%></p></td>
            </tr> 
            <tr>
                <td class="td1"><p class="value-td1">Retrait:</p></td>
                <td class="td2"><p class="value-td2"><%=article.getLieuRetrait().getRue() %>, <%=article.getLieuRetrait().getCodePostal()%>, <%=article.getLieuRetrait().getVille() %>   %></p></td>
            </tr>
            <tr>
                <td class="td1"><p class="value-td1">Vendeur:</p></td>
                <td class="td2"><p class="value-td2"><%=article.getVendeur().getPseudo() %></p></td>
            </tr> 
            <tr>
              <td class="td1"><p class="value-td1">Téléphone:</p></td>
              <td class="td2"><p class="value-td2"><%=article.getVendeur().getTelephone() %></p></td>
          </tr> 
        </table>

        <div>
          <a href = "<%=request.getContextPath()%>/accueilConnected"><button class="btn-login" type="button"> 
            back
          </button></a>
        </div>

    </div>
</body>
</html>