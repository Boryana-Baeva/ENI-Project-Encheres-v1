<%@page import="fr.eni.encheres.bll.UtilisateurManager"%>
<%@page import="fr.eni.encheres.bo.Utilisateur"%>
<%@ page language="java" contentType="text/html;"
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
	   	<link rel="stylesheet" href="styles/profil.css">
        <title>ENI-Enchères/Mon Profil</title>
    </head>

    <body>

     <!-- Navbar -->
 	<%@ include file = "navbar.jsp" %>
 	
      <!--Head-->
      <div class="head">
        <h1>Profile</h1>
      </div>

	<%if(session.getAttribute("ConnectedUser")!=null){
		Utilisateur connectedUser = (Utilisateur) session.getAttribute("ConnectedUser");
	 	Utilisateur utilisateur = UtilisateurManager.selectUserById(connectedUser.getId()); %>

    <section class="profile">
        <table>
        <tr>
            <td class="td1"><p class="value-td1">Pseudo : </p></td>
            <td class="td2"><p class="value-td2"><%=utilisateur.getPseudo() %></p></td>
        </tr>
        <tr>
            <td class="td1"><p class="value-td1">Nom : </p></td>
            <td class="td2"><p class="value-td2"><%=utilisateur.getNom()%></p></td>
        </tr>
        <tr>
            <td class="td1"><p class="value-td1">Prénom :</td>
            <td class="td2"><p class="value-td2"><%=utilisateur.getPrenom()%></td>
        </tr>
        <tr>
            <td class="td1"><p class="value-td1">Email : </p></td>
            <td class="td2"><p class="value-td2"><%=utilisateur.getEmail() %></p></td>
        </tr>    
        <tr>
            <td class="td1"><p class="value-td1">Téléphone : </p></td>
            <%if(utilisateur.getTelephone()!=null) {%>
            <td class="td2"><p class="value-td2"><%=utilisateur.getTelephone() %></td>
            <%} else { %>
            <td class="td2"><p class="value-td2"></td> 
            <% } %>
        </tr>
        <tr>
            <td class="td1"><p class="value-td1">Rue : </p></td>
            <td class="td2"><p class="value-td2"><%=utilisateur.getRue() %></p></td>
        </tr>
        <tr>
            <td class="td1"><p class="value-td1">Code Postal : </p></td>
            <td class="td2"><p class="value-td2"><%=utilisateur.getCodePostal() %></p></td>
        </tr> 
        <tr>
            <td class="td1"><p class="value-td1">Ville : </p></td>
            <td class="td2"><p class="value-td2"><%=utilisateur.getVille() %></p></td>
        </tr>
    </table>
    <% if(connectedUser.getId()==utilisateur.getId() ){%>
    <div class=btn-large>
        <a href ="<%=request.getContextPath()%>/updateProfil"><button class="btn" type="button"> <!--change that with index.html file location-->
          Modifier
        </button>
        </a>
    </div>
    <div class=btn-large>
    	<form action="<%=request.getContextPath()%>/deleteProfil" method="post">
    		<input class="btn" type="submit" name="buttonDelete" value="Supprimer mon compte"/>
    	</form>       
    </div>
</section>
<%}
}%>
</body>
</html>