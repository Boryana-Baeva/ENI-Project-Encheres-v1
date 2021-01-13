

<!-- Connected page  -->

 	<% if( session.getAttribute("ConnectedUser") != null){ %>
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
              <li><a href="PageListeEncheresNonConnecte.html">Enchères  <i class="fas fa-bullhorn"></i></a></li>
              <li><a href="PageConnexion.html">Vendre  <i class="fas fa-hand-holding-usd"></i></a></li>
              <li><a href="PageConnexion.html">Mon Profil  <i class="fas fa-user-circle"></i></a></li>
              <li><a href="<%=request.getContextPath()%>/S">Deconnexion  <i class="fas fa-sign-in-alt"></i></a></li>
          </ul>
      </div>
    </nav>
    <!--Banner-->
    <div class="header">
    </div>
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
                <li><a href="PageConnexion.html">Créer Un Compte / Se Connecter  <i class="fas fa-sign-in-alt"></i></a></li>
            </ul>
        </div>
    </nav>
    <% } %>