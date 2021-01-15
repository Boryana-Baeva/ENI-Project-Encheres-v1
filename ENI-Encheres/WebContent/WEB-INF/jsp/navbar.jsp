

<!-- Connected page  -->
<!-- commit -->
 	<%@page import="fr.eni.encheres.bo.Utilisateur"%>
<% if( session.getAttribute("ConnectedUser") != null){ %>
 		<!--Navbar-->
 
    <nav class="navbar">
      <div class="brand-title"> <a href="<%=request.getContextPath()%>/accueilConnected"> Eni-Enchères </a></div>
      
      <a href="#" class="toggle-button">
          <span class="bar"></span>
          <span class="bar"></span>
          <span class="bar"></span>
      </a>
     
      <div class="navbar-links">
      <%Utilisateur connectedUser = (Utilisateur) session.getAttribute("ConnectedUser"); %>
          <ul>
              <li><a href="PageListeEncheresNonConnecte.html">Enchères  <i class="fas fa-bullhorn"></i></a></li>
              <li><a href="<%=request.getContextPath()%>/vendre">Vendre  <i class="fas fa-hand-holding-usd"></i></a></li>
              <li><a href="<%=request.getContextPath()%>/profil?pseudo=<%=connectedUser.getPseudo()%>">Mon Profil  <i class="fas fa-user-circle"></i></a></li>
              <li><a href="<%=request.getContextPath()%>/AccueilDeconnected">Deconnexion  <i class="fas fa-sign-in-alt"></i></a></li>
          </ul>
      </div>
    </nav>
 
 	<% } else {  %>

   
<!-- Deconnected page -->

 <!--Navbar-->
 
 <nav class="navbar">
        <div class="brand-title">Eni-Enchères</div>
        
        <a href="#" class="toggle-button">
            <span class="bar"></span>
            <span class="bar"></span>
            <span class="bar"></span>
        </a>
        <div class="navbar-links">
            <ul>
                <li><a href="<%=request.getContextPath()%>/register"> Créer Un Compte <i class="fas fa-sign-in-alt"></i></a></li>
                 <li><a href="<%=request.getContextPath()%>/login"> Se Connecter  <i class="fas fa-sign-in-alt"></i></a></li>
            </ul>
        </div>
    </nav>
    <% } %>
    
       <!--Banner-->
    <div class="header">
    </div>