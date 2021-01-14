<%@ page language="java" contentType="text/html;"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang= fr>
    <head>
    	<meta charset="UTF-8">
  	    <meta name="viewport"content="width=device-width, initial-scale=1.">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <!--StyleSheet-->
        <link rel="stylesheet" href="styles/style.css">
        <link rel="stylesheet" href="styles/navbar.css">
        <link rel="stylesheet" href="styles/register.css">
        <!--Font Awesome CDN-->
        <script src="https://kit.fontawesome.com/1cbc331981.js" crossorigin="anonymous"></script>
        <!--Script-->
        <script src="script.js" defer></script>
        <title>ENI-Enchères|Sign-In</title>
    </head>
<body>
	<!-- Navbar -->
 	<%@ include file = "navbar.jsp" %>
 	
 	 <!--Head-->
    <div class="head">
      <h1>Création De Compte</h1>
    </div>

    <!--Form Créer un compte-->
    <section class="register-form">
      <form class="register" action="<%=request.getContextPath() %>/register" method="post">
        <div class="input-field">
          <label for="nom">Nom :</label>
          <input class="input" name="nom" id="nom" required>
        </div> 

        <div class="input-field">
            <label for="prenom">Prénom :</label>
            <input class="input" name="prenom" id="prenom" required>
        </div>

        <div class="input-field">
          <label for="pseudo">Pseudo :</label>
          <input class="input" name="pseudo" id="pseudo" required>
        </div>

        <div class="input-field">
          <label for="email">Email :</label>
          <input class="input" name="email" id="email" type="email" required>
        </div>

        <div class="input-field">
          <label for="mdp">Mot de passe :</label>
          <input class="input" name="mdp" id="mdp" type="password" required>
        </div>

        <div class="input-field">
          <label for="mdpConf">Confirmation :</label>
          <input class="input" name="mdpConf" id="mdpConf" type="password" required>
        </div>

        <div class="input-field">
          <label for="tel">Teléphone :</label>
          <input class="input" name="tel" id="tel" type="tel" maxlength= 10  minlength= 10 >
        </div>
        
		<div class="input-field">
		   <label for="rue">Rue :</label>
		   <input class="input" name="rue" id="rue" type="text" required>
		</div>
		
        <div class="input-field">
          <label for="ville">Ville :</label>
          <input class="input" name="ville" id="ville" type="text" required>
        </div>

        <div class="input-field">
            <label for="cp">Code Postale :</label>
            <input class="input" name="cp" id="cp" type="number" maxlength="5"  minlength="5" required>
        </div>

        

      <div class="btn-sorter">
        <div>
          <button class="btn" type="submit">Créer Mon Compte</button> </a>
        </div>
        <div>
          <a href ='PageConnexion.html'><button class="btn" type="button"> <!--change that with index.html file location-->
            Annuler
          </button>
          </a>
        </div>
      </div>
      
      </form>

    </section>

    <!--  travailler le script pour que mdp = mdpConf
    <script type="text/javascript">
        function confirmEmail() {
            var mdp = document.getElementById("mdp").value
            var mdpConf = document.getElementById("mdpConf").value
            if(mdp != mdpConf) {
                alert('Password Not Matching!');
            }
        }
    </script>
    -->
 	

</body>
</html>