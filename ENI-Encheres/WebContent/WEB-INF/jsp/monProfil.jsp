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
    <%if(session.getAttribute("ConnectedUser")!=null){
	Utilisateur connectedUser = (Utilisateur) session.getAttribute("ConnectedUser");
 	 Utilisateur utilisateur = UtilisateurManager.selectUserById(); %>
    <section class="Profil">
   
   
        <table>
        <tr>
            <td>Pseudo :</td>
            <td><%=utilisateur.getPseudo() %></td>
        </tr>

        <tr>
            <td>Nom :</td>
            <td><%=utilisateur.getNom()%></td>
        </tr>
        
         <tr>
            <td>Prénom :</td>
            <td><%=utilisateur.getPrenom()%></td>
        </tr>

        <tr>
            <td>Email :</td>
            <td><%=utilisateur.getEmail() %></td>
        </tr>
             
        <tr>
            <td>Téléphone</td>
            <%if(utilisateur.getTelephone()!=null) {%>
            <td><%=utilisateur.getTelephone() %></td>
            <%} else { %>
            <td></td> 
            <% } %>
        </tr> 

        <tr>
            <td>Rue :</td>
            <td><%=utilisateur.getRue() %></td>
        </tr>

        <tr>
            <td>Code postal :</td>
            <td><%=utilisateur.getCodePostal() %></td>
        </tr>
          
        <tr>
            <td>Ville : </td>
            <td><%=utilisateur.getVille() %></td>
        </tr>
    </table>
</section>

	<% if(connectedUser.getId()==utilisateur.getId() ){%>
	<div class=btn-large>
    <a href ='PageModifierProfil.html'><button class="btn" type="button"> <!--change that with index.html file location-->
      Modifier
    </button>
    </a>
</div>
<%}
}%>

    </body>
</html>