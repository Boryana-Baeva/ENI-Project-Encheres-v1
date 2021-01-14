<%@page import="fr.eni.encheres.bll.UtilisateurManager"%>
<%@page import="fr.eni.encheres.bo.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang = fr>
<head>
     <meta charset="UTF-8">
  	 <meta name="viewport"content="width=device-width, initial-scale=1.">
  	 <meta http-equiv="X-UA-Compatible" content="ie=edge">
      <!-- Place your kit's code here -->
     <script src="https://kit.fontawesome.com/1cbc331981.js" crossorigin="anonymous"></script>
     <link rel="stylesheet" href="styles/style.css">
   	 <link rel="stylesheet" href="styles/navbar.css">
   	 
        <title>ENI-Enchères/Mon Profil</title>
    </head>

<body>
<!-- Navbar -->
 	<%@ include file = "navbar.jsp" %>
<header>
      <h1>ENI-Enchères</h1>
    </header>

    <h2>Mon Profil</h2>
    
    <%  Utilisateur user = (Utilisateur) session.getAttribute("ConnectedUser"); %>
    
    <section class="LoggingForm">
      
      <form action="<%=request.getContextPath() %>/updateProfil" method="post"> <!-- change methode to POST to get results on server, i used GET to show the result in html file-->

<!-- Colonee de gauche-->
        <div>
          <label for="psdo">Pseudo : <%=user.getPseudo()%> </label>
        </div>

        <div>
            <label for="pNom">Prénom :</label>
            <input name="prenom" id="pNom" value="<%=user.getPrenom()%>" required>
        </div>

        <div>
            <label for="tel">Teléphone :</label>
            <input name="telephone" id="tel" type="tel" 
            maxlength= 10 value="<%=user.getTelephone()%>"  required>
        </div>

        <div>
            <label for="cp">Code Postale :</label>
            <input name="codepostale" id="cp" type="number" 
            maxlength= 5  value="<%=user.getCodePostal()%>" required>
        </div>
        
        <div>
            <label for="mdp">Mot de passe actuel :</label>
            <input name="motDePasseActuel" id="mdp" type="password" required>
          </div>

        <div>
            <label for="mdp">Mot de passe :</label>
            <input name="newMotDePasse" id="mdp" type="password" required>
          </div>

<!-- Colonee de droite-->
        <div>
            <label for="nom">Nom :</label>
            <input name="nom" id="nom" value="<%=user.getNom()%>" required>
        </div>

        <div>
            <label for="email">Email :</label>
            <input name="email" id="email" type ="email" value="<%=user.getEmail()%>" required>
        </div>

        <div>
            <label for="rue">Rue :</label>
            <input name="rue" id="rue" type="text" value="<%=user.getRue()%>" required>
        </div>

        <div>
            <label for="ville">Ville :</label>
            <input name="ville" id="ville" type="text" value="<%=user.getVille()%>" required>
        </div>

        <div>
          <label for="mdpConf">Confirmation :</label>
          <input name="motDePasseConfirmation" id="mdpConf" type="password" required>
        </div>

        <p>crédit : <%=user.getCredit() %></p>

        <div class=btn-large>
          <button class="btn" type="submit" name="update">Enregistrer</button>
        </div>



      </form>

    </section>

</body>
</html>