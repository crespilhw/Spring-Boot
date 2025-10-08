document.getElementById('conversor').addEventListener('submit', async function(e) {
    e.preventDefault();

    const valor = parseFloat(document.getElementById('valor').value);
    const de = document.getElementById('de').value;
    const para = document.getElementById('para').value;

    // Limpa conteúdos antes da requisição
    document.getElementById('resultado').textContent = "";
    document.getElementById('resultadoBox').style.display = 'none';
    document.getElementById('erro').textContent = "";
    document.getElementById('erro').style.display = 'none';

    try {
        const response = await fetch('http://localhost:8080/converter', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: new URLSearchParams({
                valor: valor,
                de: de,
                para: para
            })
        });

        if (!response.ok) throw new Error('Erro na requisição');

        const data = await response.json();

        if (data.erro) {
            // Mostra erro
            const erroBox = document.getElementById('erro');
            erroBox.textContent = data.erro;
            erroBox.style.display = 'block';
            document.getElementById('resultadoBox').style.display = 'none';
        } else {
            // Mostra resultado
            const resultadoBox = document.getElementById('resultadoBox');
            resultadoBox.style.display = 'block';
            document.getElementById('resultado').textContent = 
                `Resultado: ${data.resultado} ${data.unidade}`;
            document.getElementById('erro').style.display = 'none';
        }

    } catch (err) {
        const erroBox = document.getElementById('erro');
        erroBox.textContent = "Erro: " + err.message;
        erroBox.style.display = 'block';
        document.getElementById('resultadoBox').style.display = 'none';
    }
});
