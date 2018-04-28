package core;

import java.util.ArrayList;
import java.util.Arrays;

public class Assembler
{
	static ArrayList<Label> labels = new ArrayList<>();
	static ArrayList<String> codeLines;
	static ArrayList<String> directiveLines;
	static ArrayList<String> pseudoIns = new ArrayList<>(Arrays.asList(new String[]{"move","clear","li","la","b","bal","bgt","blt","bge","ble","bgtu","beqz","beq","bne","mul","div","rem","jalr","not","nop"}));
	

	
	private static Label findLabel(String name) // finds the address of a label
	{
		for(Label lbl: labels)
		{
			if(lbl.getName().equals(name))
				return lbl;
		}
		return null;
	}

	private static boolean isPseudo(String s)
	{
		for(String in: pseudoIns)
			if(s.equals(in))
				return true;
		return false;
	}

	static void assembleProgram(String s)
	{
		if(s.contains(".text"));
			String[] fn = s.split(".text");
		directiveLines = new ArrayList<>(Arrays.asList(fn[0].trim().split("\\n+")));
		codeLines = new ArrayList<>(Arrays.asList(fn[1].trim().replaceAll(":", ":\n").split("\\n+")));	//Labels now take a whole line for themselves
		labels.clear();
		scanForLabels();
		replacePseudo();
		assembleLines();
	}

	static void scanForLabels()
	{
		for (int i = 0; i < codeLines.size();)
		{
			if(codeLines.get(i).contains(":"))
			{
				labels.add(new Label(codeLines.get(i).split(":")[0].trim(), Integer.toBinaryString(i * 4)));
				codeLines.set(i,"");
				continue;
			}
			i++;
		}
		codeLines.removeIf(String::isEmpty);
	}

	static void replacePseudo()
	{
		for(int i=0;i<codeLines.size();i++)
		{	String c_lo =null;
			String[] pseudoData =null;
			String newLine =codeLines.get(i).trim();
			String[] sliced = newLine.split("\\s+", 2);
			if(isPseudo(sliced[0]))
				switch(sliced[0])
				{
					case "move":
						pseudoData = sliced[1].split(",");
						codeLines.set(i,"addiu "+pseudoData[0]+","+pseudoData[1]+"0");
						break;
					case "clear":
						pseudoData = sliced[1].split("\\s+");
						codeLines.set(i,"addu "+pseudoData[0]+"$zero,$zero");
						break;
					case "li":
						pseudoData = sliced[1].split(",");
						int dec_1 = Integer.parseInt(pseudoData[1]);
						String y =Integer.toBinaryString(dec_1);
						if(y.length()<=16){
						if(dec_1>=0)
						{
							for(int l =y.length();l<16;l++)
							{
							 c_lo="0"+y;
							}
						}
						else for(int l =y.length();l<16;l++)
						{
							 c_lo="1"+y;
						}}
						else{
							int s=y.length()-16;
							 c_lo= y.substring(s);

						}
						codeLines.set(i,"addiu "+pseudoData[0]+",$zero,"+c_lo);
						break;
					case "la":
						pseudoData = sliced[1].split(",");
						int dec = Integer.parseInt(pseudoData[1]);
						String x =Integer.toBinaryString(dec);
						if(dec>=0)
						{
							for(int l =x.length();l<31;l++)
							{
								x="0"+x;
							}
						}
						else for(int l =x.length();l<31;l++)
						{
							x="1"+x;
						}
						String A_hi=x.substring(0,17);
						String A_lo=x.substring(16);
						codeLines.set(i,"ori "+pseudoData[0]+","+pseudoData[0]+','+A_lo);
						codeLines.add(i,"lui"+pseudoData[0]+','+A_hi);
						break;
					case "b":
						pseudoData = sliced[1].split("\\s+");
						codeLines.set(i,"beq $zero, $zero,"+pseudoData);
						break;
					/*case "bal":

						break;*/
					case "bgt":
						pseudoData = sliced[1].split(",");
						codeLines.set(i,"bne $at, $zero,"+pseudoData[2]);
						codeLines.add(i,"slt $at,"+pseudoData[1]+","+pseudoData[0]);
						break;
					case "blt":
						pseudoData = sliced[1].split(",");
						codeLines.set(i,"bne $at, $zero,"+pseudoData[2]);
						codeLines.add(i,"slt $at,"+pseudoData[0]+","+pseudoData[1]);

						break;
					case "bge":
						pseudoData = sliced[1].split(",");
						codeLines.set(i,"beq $at, $zero,"+pseudoData[2]);
						codeLines.add("slt $at,"+pseudoData[0]+","+pseudoData[1]);
						break;
					case "ble":
						pseudoData=sliced[1].split(",");
						codeLines.set(i,"beq $at,$zero,"+pseudoData[2]);
						codeLines.add(i,"slt $at,"+pseudoData[1]+','+pseudoData[0]);
						break;
					case "bgtu":
						pseudoData=sliced[1].split(",");
						codeLines.set(i,"bne $at, $zero,"+pseudoData[2]);
						codeLines.add(i,"sltu $at,"+pseudoData[1]+','+pseudoData[0]);
						break;
					case "beqz":
						pseudoData=sliced[1].split(",");
						codeLines.set(i,"beq"+pseudoData[0]+",$zero,"+pseudoData[1]);
						break;
					case "beq":
						pseudoData=sliced[1].split(",");
						codeLines.set(i,"beq "+pseudoData[0]+", $at,"+pseudoData[2]);
						codeLines.add(i,"ori ,$at, $zero"+pseudoData[1]);
						break;
					case "bne":
						pseudoData=sliced[1].split(",");
						codeLines.set(i,"beq "+pseudoData[0]+", $at,"+pseudoData[2]);
						codeLines.add(i,"ori ,$at, $zero"+pseudoData[1]);
						break;
					case "mul":
						pseudoData=sliced[1].split(",");
						codeLines.set(i,"mflo "+pseudoData[0]);
						codeLines.add(i,"mult "+pseudoData[1]+","+pseudoData[2]);
						break;
					case "div":
						pseudoData=sliced[1].split(",");
						codeLines.set(i,"mflo "+pseudoData[0]);
						codeLines.add(i,"div "+pseudoData[1]+","+pseudoData[2]);
						break;
					case "rem":
						pseudoData=sliced[1].split(",");
						codeLines.set(i,"mfhi "+pseudoData[0]);
						codeLines.add(i,"div "+pseudoData[1]+","+pseudoData[2]);

						break;
					case "jalr":
						pseudoData=sliced[1].split("\\s+",2);
						codeLines.set(i,"jalr"+pseudoData[1]+",$ra");
						break;
					case "not":
						pseudoData=sliced[1].split(",");

						codeLines.set(i,"nor "+pseudoData[0]+","+pseudoData[1]+",$zero");
						break;
					case "nop":
						codeLines.set(i,"sll $zero, $zero, 0");
				}

		}
	}

static void scanForDirectives()
{
	for(int j=0; j<directiveLines.size();j++)
	{
		String varName = null;
		String directiveName = null;
		String directiveData = null;

		String newLine = directiveLines.get(j);
		if(newLine.contains(":"))
		{
			varName = newLine.split(":")[0].trim();
			if(newLine.contains("."))
			directiveName=newLine.split(".",2)[1].split("\\s+")[0];
		}
		switch ( directiveName)
		{

			case "ascii":
				directiveData=newLine.split(".",2)[1].split("\"")[1];
				Memory.saveString(directiveData,varName,false);
				break;
			case "asciiz":
				directiveData=newLine.split(".",2)[1].split("\"")[1];
				Memory.saveString(directiveData,varName,true);
				break;
			case "byte":
				directiveData=newLine.split(".",2)[1].split("\\s+",2)[1];
				for(String b: directiveData.split(","))
				Memory.saveB(varName,b.trim());
				break;

			case "half":
				directiveData=newLine.split(".",2)[1].split("\\s+",2)[1];
				for(String b: directiveData.split(","))
				Memory.saveH(varName,b.trim());
				break;
			case "space":
				directiveData=newLine.split(".",2)[1].split("\\s+",2)[1];
				int value = Integer.parseInt(directiveData);
				Memory.saveArrayEmpty(value,varName);
				break;

			case "word":
				directiveData=newLine.split(".",2)[1].split("\\s+",2)[1];
				for(String b: directiveData.split(","))
					Memory.saveW(varName,b.trim());
				break;


		}
	}

}
	private static void assembleLines()
	{
		for(int i = 0; i < codeLines.size(); i++)
		{
			String str = null;
			String s, t, d, imm, a;
			str = codeLines.get(i).trim();
			s = t = d = imm = a = null;
			String[] ss = str.replaceAll(",\\s+|\\s+", " ").split("\\s");
			Instruction instruction = Instruction.searchInstruction(ss[0].trim());
			if (instruction != null)
			{
				String syn = instruction.getSyntaxAsString(instruction);
				syn = syn.replaceAll("f+|o+", instruction.opc);
				switch (instruction.syn)
				{
					case ArithLog:
					case JumpR:
					case DivMult:
					case ShiftV:
					case MoveFrom:
					case MoveTo:
					case Shift:
						s = (instruction.syn == Syntax.JumpR || instruction.syn == Syntax.DivMult || instruction.syn == Syntax.MoveTo) ? RegisterFile.findRegister(ss[1]).address : (instruction.syn == Syntax.ShiftV) ? RegisterFile.findRegister(ss[3]).address : (instruction.syn != Syntax.MoveFrom) ? RegisterFile.findRegister(ss[2]).address : "00000";
						t = (instruction.syn == Syntax.JumpR || instruction.syn == Syntax.MoveFrom || instruction.syn == Syntax.MoveTo || instruction.syn == Syntax.Shift) ? "00000" : (instruction.syn == Syntax.DivMult || instruction.syn == Syntax.ShiftV ) ? RegisterFile.findRegister(ss[2]).address : RegisterFile.findRegister(ss[3]).address;
						d = (instruction.syn == Syntax.JumpR || instruction.syn == Syntax.DivMult || instruction.syn == Syntax.MoveTo) ? "00000" : RegisterFile.findRegister(ss[1]).address;
						a = (instruction.syn == Syntax.Shift) ? Integer.toBinaryString(Integer.parseInt(ss[3])) : "00000";
						break;
					case LoadI:	//Immediate
					case ArithLogI:	//Immediate
						t = RegisterFile.findRegister(ss[1]).address;
						s = (instruction.syn == Syntax.LoadI) ? "00000" : RegisterFile.findRegister(ss[2]).address;
						imm = (instruction.syn == Syntax.LoadI) ? Integer.toBinaryString(Integer.parseInt(ss[2])) : Integer.toBinaryString(Integer.parseInt(ss[3]));
						break;
					case LoadStore:	//Immediate
						t = RegisterFile.findRegister(ss[1]).address;
						imm = Integer.toBinaryString(Integer.parseInt(ss[2].split("\\(")[0]));
						s = RegisterFile.findRegister(ss[2].split("\\(")[1].split("\\)")[0]).address;
						break;
					case BranchZ:	//Immediate
					case Branch:	//Immediate
						s = RegisterFile.findRegister(ss[1]).address;
						t = RegisterFile.findRegister(ss[2]).address;
						Label l = findLabel(ss[3].trim());
						if (l != null)
							imm = Integer.toBinaryString((Integer.parseInt(l.getAddress(), 2)/4) - (i + 1));
					case Jump:	//Jump
						Label l2 = findLabel(ss[1]);
						if (l2 != null)
							imm = l2.getAddress().substring(4, 30);
						break;
				}

				if (a != null)
					a = SignExtend.extendUnsigned(a, 5);
				if (imm != null)
					imm = SignExtend.extendUnsigned(imm, 16);
				if (imm != null && instruction.syn == Syntax.Jump)
					imm = SignExtend.extendUnsigned(imm, 26);


				if (imm != null)
					syn = syn.replaceAll("i+", imm);
				if (d != null)
					syn = syn.replaceAll("d+", d);
				if (t != null)
					syn = syn.replaceAll("t+", t);
				if (a != null)
					syn = syn.replaceAll("a+", a);
				if (s != null)
					syn = syn.replaceAll("s+", s);
				if (syn != null)
				{
					InstructionMemory.add(syn);
				}
			}
		}
	}
}
class Label
{
	String name;
	String address;
	public Label(String name, String address)
	{
		this.name = name;
		while(address.length() < 32)
			address = "0" + address;
		this.address = address;
	}
	public String getName()
	{
		return this.name;
	}
	public String getAddress()
	{
		return this.address;
	}
}

enum Encoding
{Register,Immediate,Jump}

enum Syntax
{ArithLog,DivMult,Shift,ShiftV,JumpR,MoveFrom,MoveTo,ArithLogI,LoadI,Branch,BranchZ,LoadStore,Jump,Trap}

