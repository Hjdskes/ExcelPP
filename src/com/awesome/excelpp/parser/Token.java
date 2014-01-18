package com.awesome.excelpp.parser;

/**
 * <p>A token is the smallest single entity on which the parser will operate.
 * An example of a token is a function name or a number.
 * All Tokens have a <code>TokenType</code> as defined in the enum <code>TokenType</code>
 * and a String containing the contents of the token.</p>
 * @author Team Awesome
 */
public class Token {
    public TokenType type;
    public String data;

    /**
     * Creates a new Token.
     * @param type The {@link TokenType} for this {@link Token}
     * @param data The String stored in this {@link Token}
     */
    public Token(TokenType type, String data) {
        this.type = type;
        this.data = data;
    }

    /**
     * Returns a String representation of this Token.
     * @return The String representation of this Token
     */
    @Override
    public String toString() {
        return String.format("(%s %s)", type.name(), data);
    }
}
