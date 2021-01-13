<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ page import="fr.eni.encheres.bo.ArticleVendu" %>
<%@page import="java.util.List"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles/style.css">
    <link rel="stylesheet" href="styles/navbar.css">
    <link rel="stylesheet" href="styles/listeEncheres.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />
    <script src="script.js" defer></script>
    <title>Document</title>
</head>
<body>
	<!-- Navbar -->
 	<%@ include file = "navbar.jsp" %>
 	
 	<div class="search-bar">
        
        <select class="categories" name="pets" id="pet-select">
            <option value="">Toutes nos catégories</option>
            <option value="dog">Dog</option>
            <option value="cat">Cat</option>
            <option value="hamster">Hamster</option>
            <option value="parrot">Parrot</option>
            <option value="spider">Spider</option>
            <option value="goldfish">Goldfish</option>
        </select>
        
        <div class="search-box">
            <input type="text" class="search" placeholder="What are we looking for today?">
        </div>

        <button type="submit" class="search-btn">
            <i class="fas fa-search"></i>
        </button>
    </div>
    
    <div class="recherche-sup">


            <div>
                    <input type="radio" id="huey" name="drone" value="huey"
                        checked>
                    <label for="huey">Achats</label>
            
                <ul>
                    <li>
                        <input name="Ssdm" id="Ssdm" type="radio">
                        <label class="ssdm" for="Ssdm">Enchères ouvertes</label>
                    </li>

                    <li>
                        <input name="Ssdm" id="Ssdm" type="radio">
                        <label class="ssdm" for="Ssdm">Mes enchères en cours</label>
                    </li>
                    
                    <li>
                        <input name="Ssdm" id="Ssdm" type="radio">
                        <label class="ssdm" for="Ssdm">Mes enchères remportées</label>
                    </li>
                </ul>
            </div>


            <div>
                <input type="radio" id="huey" name="drone" value="huey"
                       checked>
                <label for="huey">Mes Ventes</label>
            

            <ul>
                <li>
                    <input name="Ssdm" id="Ssdm" type="radio">
                    <label class="ssdm" for="Ssdm">Mes ventes en cours</label>
                </li>
                
                <li>
                    <input name="Ssdm" id="Ssdm" type="radio">
                    <label class="ssdm" for="Ssdm">Ventes non débutés</label>
                </li>

                <li>
                    <input name="Ssdm" id="Ssdm" type="radio">
                    <label class="ssdm" for="Ssdm">Ventes terminées</label>
                </li>
            </ul>

            </div>       
    </div>
    
    <div class="head">
        <h1>Enchères En Cours</h1>
    </div>
    <div class="container">
        <div class="card-grid">
        
        <% List<ArticleVendu> listeArticles = (List<ArticleVendu>)request.getAttribute("listeArticles"); %>
	        <% if(listeArticles.size() != 0) { %>
	        <% for(ArticleVendu article : listeArticles) { %> 
	            <div class="card">
	                <div class="card-header">
	                    <h1><%=article.getNom()%></h1>
	                </div>
	                <div class="card-img-container">
	                    <img src="img/tournevis.jpeg" alt="">
	                </div>
	                <div class="card-body">
	                    <div class="prix"><i class="fas fa-tag"></i><%=article.getMiseAPrix()%></div>
	                    <div class="card-date-enchere">
	                        <p><i class="far fa-clock"></i><%=article.getDateDebutEncheres()%></p>
	                    </div>
	                    <div class="vendeur">
	                        <p><i class="fas fa-id-badge"></i> <a href="#"><%=article.getVendeur()%></a></p>
	                    </div>
	                </div>
	            </div>
            <% } %>
		<% } %>
            
        </div>
    </div>
</body>
</html>