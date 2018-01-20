# app-stand-auto

Aplicação Gestão de Stock de Carros.

*A parte de ir buscar algo a internet, era inicialmente ir buscar um carro usado para adicionar ao stock, mas decidi alterar para ir buscar revendedores, porque não encontrava API's gratuitas que forneciam essa informação.

*A parte de fornecedor e a financeira não foram inicialmente pensadas em inclir, mas foram incluidas consoante o tempo restante diponível após a conclusão das anteriores.  

Funcionalidades totais atualizadas:

Parte Gestão de stock.

- 1º Ecrã - Mostra todos os carros em stock.
- 2º Ecrã - Adiciona a stock.
- 3º Ecrã - Mostra toda informação espefífica .
			+ Elimina carro do stock.
			+ Atualiza informação específiaca do carro em stock.
			+ 4º Ecrã(Popup) - Permite Vender Carro, isto é, retira do stock e adiciona as vendas para calcular lucros.

Parte Fornecedor:

- 1º Ecrã - Mostra todos os fornecedores.
- 2º Ecrã - Permite Adicionar fornecdor.
- 3º Ecrã - Mostra informação específica do fornecedor (ao clickar 2 vezes na morada abre google maps no local da morada).
			+ Elimina Fornecedor.
			+ Atualiza informação específiaca do fornecedor.

Parte Fornecedor para Revenda:
			
*O objetivo era utilizar a internet para ir buscar informação de fornecedores de revenda.

- Permite receber a informação de revendedores(endereço e nome das fábricas das marcas que foi possível retirar informação da API).
- Ao clickar no item (fábrica + endereço) abre o googlemaps e enconra a localização espefífica. 		
		
Parte Faturamento:

- 1º Ecrã - 
	Permite: 
		1- Saber o Valor total do stock.
		2- Saber o Valor total investido no stock.
		3- Saber o Valor total das vendas.
		4- Calcular o Lucro por mês, isto é, calcula o saldo do mês depois de escolher o mês e o ano, depois é representado o valor relativo as despesas desse mês/ano e por fim calcula o lucro com base na diferênca Vendas-Despesas.

