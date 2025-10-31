<?php
    session_start();
?>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tela de Login</title>
    <link rel="stylesheet" href="./assets/css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100&display=swap" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

</head>
<body>
    <header>
    <h1 id="um">SEJA BEM-VINDO</h1>
    <h3 id="dois">ENTRE COM SUA CONTA DO CHECKCAR</h3>
    </header>
    <main>
        <h2 id="tres"> LOGIN </h2>

        <form action="./actions/testLogin.php" method="POST">
            <input type="email" name="email" id="email" class="form-control" placeholder="Digite seu E-mail" required>
            <input type="password" placeholder="Digite a sua senha" name="senha" id="senha" required>
            <input type="submit" name="submit" id="submit" value="Fazer Login">
            <button type="button" id="bg" >Esqueci minha senha</button>
        </form>
        

    </main>
</body>
</html>
