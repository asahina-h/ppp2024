package com.example.animalfortune;

public class AnimalFortune
{
	private String fortune; // 運勢
	private String meaning; // 意味
	private String tarotCard; // 対応タロットカード
	private String cardDescription; // タロットカードの説明
	private String animalCharacter; // 動物占いキャラ

	// コンストラクタ
	public AnimalFortune(String fortune, String meaning, String tarotCard, String cardDescription, String animalCharacter)
	{
		this.fortune = fortune;
		this.meaning = meaning;
		this.tarotCard = tarotCard;
		this.cardDescription = cardDescription;
		this.animalCharacter = animalCharacter;
	}

	// getter/setter
	public String getFortune()
	{
		return fortune;
	}

	public void setFortune(String fortune)
	{
		this.fortune = fortune;
	}

	public String getMeaning()
	{
		return meaning;
	}

	public void setMeaning(String meaning)
	{
		this.meaning = meaning;
	}

	public String getTarotCard()
	{
		return tarotCard;
	}

	public void setTarotCard(String tarotCard)
	{
		this.tarotCard = tarotCard;
	}

	public String getCardDescription()
	{
		return cardDescription;
	}

	public void setCardDescription(String cardDescription)
	{
		this.cardDescription = cardDescription;
	}

	public String getAnimalCharacter()
	{
		return animalCharacter;
	}

	public void setAnimalCharacter(String animalCharacter)
	{
		this.animalCharacter = animalCharacter;
	}
}
