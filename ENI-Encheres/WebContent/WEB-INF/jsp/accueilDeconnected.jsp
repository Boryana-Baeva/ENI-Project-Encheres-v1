<!DOCTYPE html>
<%@page import="fr.eni.encheres.bll.CategorieManager"%>
<%@page import="fr.eni.encheres.bo.Categorie"%>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles/style.css">
    <link rel="stylesheet" href="styles/navbar.css">
    <link rel="stylesheet" href="styles/accueil.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />
    <script src="script.js" defer></script>
    <title>Document</title>
</head>
<body>
  <!-- Navbar -->
 	<%@ include file = "navbar.jsp" %>

    <div class="search-bar">
        
        <select class="input" id="categorie">
            <% for(Categorie categorie : CategorieManager.selectionnerToutesLesCategories()) { %>
            <option name="categorie" value ="<%=categorie.getId()%>"><%=categorie.getLibelle()%></option>
            <% } %>
         </select>
        
        <div class="search-box">
            <input type="text" class="search" placeholder="What are we looking for today?">
        </div>

        <button type="submit" class="search-btn">
            <i class="fas fa-search"></i>
        </button>
	</div>

    <div class="head">
        <h1>Enchères En Cours</h1>
    </div>
    <div class="container">
        <div class="card-grid">

            <div class="card">
                <div class="card-header">
                    <h1>Article-1</h1>
                </div>
                <div class="card-img-container">
                    <img src="img/tournevis.jpeg" alt="">
                </div>
                <div class="card-body">
                    <div class="prix"><i class="fas fa-tag"></i> 150</div>
                    <div class="card-date-enchere">
                        <p><i class="far fa-clock"></i> 12/05/2021</p>
                    </div>
                    <div class="vendeur">
                        <p><i class="fas fa-id-badge"></i> <a href="#">Bob</a></p>
                    </div>
                </div>
            </div>

            <div class="card">
                <div class="card-header">
                    <h1>Article-1</h1>
                </div>
                <div class="card-img-container">
                    <img src="img/tournevis.jpeg" alt="">
                </div>
                <div class="card-body">
                    <div class="prix"><i class="fas fa-tag"></i> 150</div>
                    <div class="card-date-enchere">
                        <p><i class="far fa-clock"></i> 12/05/2021</p>
                    </div>
                    <div class="vendeur">
                        <p><i class="fas fa-id-badge"></i> <a href="#">Bob</a></p>
                    </div>
                </div>
            </div>

            <div class="card">
                <div class="card-header">
                    <h1>Article-2</h1>
                </div>
                <div class="card-img-container">
                    <img src="img/tournevis.jpeg" alt="">
                </div>
                <div class="card-body">
                    <div class="prix"><i class="fas fa-tag"></i> 150</div>
                    <div class="card-date-enchere">
                        <p><i class="far fa-clock"></i> 12/05/2021</p>
                    </div>
                    <div class="vendeur">
                        <p><i class="fas fa-id-badge"></i> <a href="#">Bob</a></p>
                    </div>
                </div>
            </div>

            <div class="card">
                <div class="card-header">
                    <h1>Article-3</h1>
                </div>
                <div class="card-img-container">
                    <img src="img/pc.jpg" alt="">
                </div>
                <div class="card-body">
                    <div class="prix"><i class="fas fa-tag"></i> 150</div>
                    <div class="card-date-enchere">
                        <p><i class="far fa-clock"></i> 12/05/2021</p>
                    </div>
                    <div class="vendeur">
                        <p><i class="fas fa-id-badge"></i> <a href="#">Bob</a></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>