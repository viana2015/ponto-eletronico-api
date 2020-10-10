INSERT INTO `empresa` (`id`, `cnpj`, `data_atualizacao`, `data_criacao`, `razao_social`) 
VALUES (NULL, '74245715000140', CURRENT_DATE(), CURRENT_DATE(), 'Jrcg Consultoria');

INSERT INTO `funcionario` (`id`, `cpf`, `data_atualizacao`, `data_criacao`, `email`, `nome`, 
`perfil`, `qtd_horas_almoco`, `qtd_horas_trabalho_dia`, `senha`, `valor_hora`, `empresa_id`) 
VALUES (NULL, '08583582718', CURRENT_DATE(), CURRENT_DATE(), 'admin@jrcg.com', 'ADMIN', 'ROLE_ADMIN', NULL, NULL, 
'$2a$10$txT65yH1gDl6mdXZJrg92.1fxM2sVsWtwXQiwhEMVupPRYFoPwHFK', NULL, 
(SELECT `id` FROM `empresa` WHERE `cnpj` = '74245715000140'));