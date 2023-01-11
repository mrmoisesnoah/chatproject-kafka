package br.com.dbc.vemser.produtor.enums;

public enum ChatName {
    AA_GERAL("ca3xrcl7-chat1", 0),
    ALISON("ca3xrcl7-chat1", 1),
    BRUNO("ca3xrcl7-chat1", 2),
    EDUARDO("ca3xrcl7-chat1", 3),
    LINCK("ca3xrcl7-chat1", 4),
    LUCENA("ca3xrcl7-chat1", 5),
    JEAN("ca3xrcl7-chat1", 6),
    JHENNY("ca3xrcl7-chat1", 7),
    JONAS("ca3xrcl7-chat1", 8),
    JULIO("ca3xrcl7-chat1", 9),
    KAIO("ca3xrcl7-chat2", 0),
    LUCAS("ca3xrcl7-chat2", 1),
    FELIPE("ca3xrcl7-chat2", 2),
    LUIZ("ca3xrcl7-chat2", 3),
    MATHEUS("ca3xrcl7-chat2", 4),
    NOAH("ca3xrcl7-chat2", 5),
    PAULO("ca3xrcl7-chat2", 6),
    RICARDO("ca3xrcl7-chat2", 7),
    RAFA("ca3xrcl7-chat2", 8),
    MAICON("ca3xrcl7-chat2", 9);

    private String topico;
    private Integer particao;

    ChatName(String topico, Integer particao) {
        this.topico = topico;
        this.particao = particao;
    }

    public String getTopico() {
        return topico;
    }

    public Integer getParticao() {
        return particao;
    }
}
