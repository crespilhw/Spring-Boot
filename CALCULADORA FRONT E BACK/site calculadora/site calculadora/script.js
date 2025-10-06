document.getElementById('calculadora').addEventListener('submit', async function(e) {
    e.preventDefault(); // Evita o envio padrão do formulário

    // Obtém os valores dos campos do formulário
    const num1 = parseFloat(document.getElementById('num1').value);
    const num2 = parseFloat(document.getElementById('num2').value);
    const operacao = document.getElementById('operacao').value;

    // Inicializa variáveis para resultado e erro
    let resultado = "";
    let erro = "";

    // Limpa resultado e erro antes da requisição
    document.getElementById('resultado').textContent = "";
    document.getElementById('erro').textContent = "";

    try {
        const response = await fetch('http://localhost:8080/calcular', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded' // Corrigido typo aqui
            },
            body: new URLSearchParams({
                num1: num1.toString(),
                num2: num2.toString(),
                operacao: operacao
            })
        });

        if (!response.ok) {
            throw new Error('Erro na requisição');
        }

        const data = await response.json();

        if (data.erro) {
            erro = data.erro;
        } else {
            resultado = data.resultado;
        }

        document.getElementById('resultado').textContent = resultado;
        document.getElementById('erro').textContent = erro;

    } catch (err) {
        document.getElementById('erro').textContent = "Erro: " + err.message;
    }
});